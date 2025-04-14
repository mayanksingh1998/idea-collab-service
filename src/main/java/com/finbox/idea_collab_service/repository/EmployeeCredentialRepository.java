package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.EmployeeCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCredentialRepository extends JpaRepository<EmployeeCredential, Integer> {
    EmployeeCredential findEmployeeCredentialByEmployeeEmail(String employeeId);
}
