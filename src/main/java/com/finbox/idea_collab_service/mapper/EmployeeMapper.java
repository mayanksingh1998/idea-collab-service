package com.finbox.idea_collab_service.mapper;

import com.finbox.idea_collab_service.dto.request.AddEmployeeRequestDto;
import com.finbox.idea_collab_service.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public static Employee toDTO(AddEmployeeRequestDto employee) {
        if (employee == null) {
            return null;
        }

        Employee dto = new Employee();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setLocation(employee.getLocation());
        dto.setEmployeeRole(employee.getRole());
        dto.setManagerId(employee.getManagerId());

        return dto;
    }
}
