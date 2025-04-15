package com.finbox.idea_collab_service.helper;


import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.EmployeeCredential;
import com.finbox.idea_collab_service.repository.EmployeeCredentialRepository;
import com.finbox.idea_collab_service.utils.HashUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceHelperTest {

    private EmployeeCredentialRepository employeeCredentialRepository;
    private HashUtil hashUtil;
    private EmployeeServiceHelper employeeServiceHelper;

    @BeforeEach
    void setUp() {
        employeeCredentialRepository = mock(EmployeeCredentialRepository.class);
        hashUtil = mock(HashUtil.class);
        employeeServiceHelper = new EmployeeServiceHelper(employeeCredentialRepository, hashUtil);
    }

    @Test
    void testCreateEmployeeCredentials_ShouldReturnSavedCredential() {
        // Given
        String email = "john.doe@example.com";
        String password = "securePassword";
        String hashedPassword = "hashedSecurePassword";
        Employee employee = new Employee();

        when(hashUtil.getHashedPassword(password)).thenReturn(hashedPassword);
        when(employeeCredentialRepository.save(any(EmployeeCredential.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        EmployeeCredential credential = employeeServiceHelper.createEmployeeCredentials(email, password, employee);

        // Then
        assertNotNull(credential);
        assertEquals(email, credential.getEmployeeEmail());
        assertEquals(hashedPassword, credential.getPasswordHash());
        assertTrue(credential.getIsActive());
        assertNotNull(credential.getCreatedAt());
        assertNotNull(credential.getUpdatedAt());
        assertEquals(employee, credential.getEmployee());

        verify(employeeCredentialRepository, times(1)).save(credential);
        verify(hashUtil, times(1)).getHashedPassword(password);
    }
}

