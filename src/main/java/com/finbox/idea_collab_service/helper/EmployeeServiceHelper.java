package com.finbox.idea_collab_service.helper;

import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.EmployeeCredential;
import com.finbox.idea_collab_service.repository.EmployeeCredentialRepository;
import com.finbox.idea_collab_service.repository.EmployeeRepository;
import com.finbox.idea_collab_service.utils.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class EmployeeServiceHelper {

    private final EmployeeCredentialRepository employeeCredentialRepository;
    private  final HashUtil hashUtil;


    public EmployeeCredential createEmployeeCredentials(String employeeId, String password, Employee employee) {
        EmployeeCredential employeeCredential = new EmployeeCredential();
        employeeCredential.setEmployeeEmail(employeeId);
        employeeCredential.setPasswordHash(hashUtil.getHashedPassword(password));
        employeeCredential.setIsActive(true);
        employeeCredential.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        employeeCredential.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        employeeCredential.setEmployee(employee);
        employeeCredentialRepository.save(employeeCredential);
        return employeeCredential;
    }

}
