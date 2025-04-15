package com.finbox.idea_collab_service.service;


import com.finbox.idea_collab_service.dto.AuthToken;
import com.finbox.idea_collab_service.dto.reponse.LoginResponseDto;
import com.finbox.idea_collab_service.entity.Employee;
import com.finbox.idea_collab_service.entity.EmployeeCredential;
import com.finbox.idea_collab_service.exception.AuthTokenExpiredException;
import com.finbox.idea_collab_service.exception.UserNotFoundException;
import com.finbox.idea_collab_service.helper.AuthServiceHelper;
import com.finbox.idea_collab_service.repository.EmployeeCredentialRepository;
import com.finbox.idea_collab_service.service.impl.AuthServiceImpl;
import com.finbox.idea_collab_service.utils.HashUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    private EmployeeCredentialRepository employeeCredentialRepository;
    private HashUtil hashUtil;
    private AuthServiceHelper authServiceHelper;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setup() {
        employeeCredentialRepository = mock(EmployeeCredentialRepository.class);
        hashUtil = mock(HashUtil.class);
        authServiceHelper = mock(AuthServiceHelper.class);
        authService = new AuthServiceImpl(employeeCredentialRepository, hashUtil, authServiceHelper);
    }

    @Test
    public void testAuthenticate_Success() {
        String email = "test@example.com";
        String password = "password";
        String hashedPassword = "hashedPassword";
        String token = "mockedToken";

        Employee employee = new Employee();
        employee.setId("emp123");

        EmployeeCredential credential = new EmployeeCredential();
        credential.setEmployee(employee);
        credential.setPasswordHash(hashedPassword);

        when(employeeCredentialRepository.findEmployeeCredentialByEmployeeEmail(email))
                .thenReturn(Optional.of(credential));
        when(hashUtil.getHashedPassword(password)).thenReturn(hashedPassword);
        when(authServiceHelper.generateAndStoreToken("emp123")).thenReturn(token);

        LoginResponseDto response = authService.authenticate(email, password);

        assertNotNull(response);
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticate_InvalidUser() {
        String email = "nonexistent@example.com";
        when(employeeCredentialRepository.findEmployeeCredentialByEmployeeEmail(email))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authService.authenticate(email, "password"));
    }

    @Test
    public void testAuthenticate_InvalidPassword() {
        String email = "test@example.com";
        String password = "password";
        String hashedPassword = "correctHash";

        EmployeeCredential credential = new EmployeeCredential();
        credential.setPasswordHash(hashedPassword);

        when(employeeCredentialRepository.findEmployeeCredentialByEmployeeEmail(email))
                .thenReturn(Optional.of(credential));
        when(hashUtil.getHashedPassword(password)).thenReturn("wrongHash");

        LoginResponseDto response = authService.authenticate(email, password);

        assertNull(response);
    }

    @Test
    public void testAuthenticateAndGetEmployeeId_ValidToken() {
        String token = "validToken";
        String empId = "emp123";
        Timestamp futureExpiry = new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000); // 10 mins later

        AuthToken authToken = new AuthToken(empId,token, futureExpiry);

        when(authServiceHelper.getAuthToken(token)).thenReturn(authToken);

        String result = authService.authenticateAndGetEmployeeId(token);

        assertEquals(empId, result);
    }

    @Test
    public void testAuthenticateAndGetEmployeeId_TokenExpired() {
        String token = "expiredToken";
        Timestamp pastExpiry = new Timestamp(System.currentTimeMillis() - 1000);

        AuthToken authToken = new AuthToken("emp123", token,pastExpiry);
        when(authServiceHelper.getAuthToken(token)).thenReturn(authToken);

        assertThrows(AuthTokenExpiredException.class, () -> authService.authenticateAndGetEmployeeId(token));
    }

    @Test
    public void testAuthenticateAndGetEmployeeId_NullToken() {
        when(authServiceHelper.getAuthToken("anyToken")).thenReturn(null);
        assertThrows(AuthTokenExpiredException.class, () -> authService.authenticateAndGetEmployeeId("anyToken"));
    }
}

