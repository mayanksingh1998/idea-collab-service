package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> getEmployeesById(String id);

}
