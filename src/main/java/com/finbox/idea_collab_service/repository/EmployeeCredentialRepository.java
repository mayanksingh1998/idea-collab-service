package com.finbox.idea_collab_service.repository;

import com.finbox.idea_collab_service.entity.EmployeeCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeCredentialRepository extends JpaRepository<EmployeeCredential, Integer> {
    Optional<EmployeeCredential> findEmployeeCredentialByEmployeeEmail(String employeeId);
}
