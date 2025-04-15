package com.finbox.idea_collab_service.service.impl;

import com.finbox.idea_collab_service.dto.reponse.EmployeeIdeasDto;
import com.finbox.idea_collab_service.dto.reponse.IdeaReactionsResponseDto;
import com.finbox.idea_collab_service.dto.request.AddEmployeeRequestDto;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.EmployeeCredential;
import com.finbox.idea_collab_service.entity.Idea;
import com.finbox.idea_collab_service.helper.EmployeeServiceHelper;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.mapper.EmployeeMapper;
import com.finbox.idea_collab_service.service.EmployeeService;
import com.finbox.idea_collab_service.service.IdeaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeManager employeeManager;

    private final EmployeeServiceHelper employeeServiceHelper;

    private final IdeaService ideaService;

    public EmployeeServiceImpl(EmployeeManager employeeManager, EmployeeServiceHelper employeeServiceHelper, IdeaService ideaService) {
        this.employeeManager = employeeManager;
        this.employeeServiceHelper = employeeServiceHelper;
        this.ideaService = ideaService;
    }

    @Override
    public Employee getEmployeeById(String id) {
        if (id != null && !id.isEmpty()) {
            return employeeManager.getEmployeeById(id);
        }
        return null;
    }

    @Override
    public Employee onboardEmployee(AddEmployeeRequestDto employee) {
        if (employee != null) {
            // Convert AddEmployeeRequestDto to Employee entity
            Employee employeeEntity = EmployeeMapper.toDTO(employee);
            //TODO: Validate password like special char, special char, etc
            EmployeeCredential employeeCredential = employeeServiceHelper.createEmployeeCredentials(employeeEntity.getEmail(), employee.getPassword(), employeeEntity);
            employeeEntity.setEmployeeCredential(employeeCredential);
            return employeeManager.persitEmployee(employeeEntity);
        }
        return null;
    }

    @Override
    public EmployeeIdeasDto getEmployeeIdeas(String employeeId) {
        List<Idea> employeeIdeas = ideaService.getIdeasByEmployeeId(employeeId);
        return EmployeeIdeasDto.builder()
                .ideas(employeeIdeas)
                .build();
    }
}
