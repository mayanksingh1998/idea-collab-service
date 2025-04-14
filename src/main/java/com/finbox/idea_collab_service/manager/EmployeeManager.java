package com.finbox.idea_collab_service.manager;

import com.finbox.idea_collab_service.entity.Employee;

public interface EmployeeManager {
    Employee persitEmployee(Employee employee);

    Employee getEmployeeById(String id);
}
