package com.finbox.idea_collab_service.manager.impl;

import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.exception.ResourceNotFoundException;
import com.finbox.idea_collab_service.manager.EmployeeManager;
import com.finbox.idea_collab_service.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
public class EmployeeManagerImpl implements EmployeeManager{

    private final EmployeeRepository employeeRepository;

    public EmployeeManagerImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee persitEmployee(Employee employee) {
        if (employee != null) {
            return employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(String id) {
        if (id != null && !id.isEmpty()) {
            return employeeRepository.getEmployeesById(id).orElseThrow(() -> new ResourceNotFoundException(
                    String.format("Idea does not exist  %s", id)));
        }
        return null;
    }
}
