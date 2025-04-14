package com.finbox.idea_collab_service.service;

import com.finbox.idea_collab_service.dto.reponse.EmployeeIdeasDto;
import com.finbox.idea_collab_service.dto.request.AddEmployeeRequestDto;
import com.finbox.idea_collab_service.entity.Employee;

public interface EmployeeService {
    // Define methods for employee-related operations here
    // For example:
     Employee getEmployeeById(String id);
     Employee onboardEmployee(AddEmployeeRequestDto employee);

     EmployeeIdeasDto getEmployeeIdeas(String employeeId);
    // void deleteEmployee(String id);
}
