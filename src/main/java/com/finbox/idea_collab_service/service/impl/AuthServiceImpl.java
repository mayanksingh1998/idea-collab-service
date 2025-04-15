package com.finbox.idea_collab_service.service.impl;

import com.finbox.idea_collab_service.dto.AuthToken;
import com.finbox.idea_collab_service.dto.reponse.LoginResponseDto;
import com.finbox.idea_collab_service.entity.EmployeeCredential;
import com.finbox.idea_collab_service.exception.AuthTokenExpiredException;
import com.finbox.idea_collab_service.helper.AuthServiceHelper;
import com.finbox.idea_collab_service.repository.EmployeeCredentialRepository;
import com.finbox.idea_collab_service.service.AuthService;
import com.finbox.idea_collab_service.utils.HashUtil;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;

@Service
public class AuthServiceImpl implements AuthService {

    private final EmployeeCredentialRepository employeeCredentialRepository;

    private final HashUtil hashUtil;

    private final AuthServiceHelper authServiceHelper;

    public AuthServiceImpl(EmployeeCredentialRepository employeeCredentialRepository, HashUtil hashUtil, AuthServiceHelper authServiceHelper) {
        this.employeeCredentialRepository = employeeCredentialRepository;
        this.hashUtil = hashUtil;
        this.authServiceHelper = authServiceHelper;
    }

    @Override
    public LoginResponseDto authenticate(String email, String password) {
        EmployeeCredential employeeCredential = employeeCredentialRepository.findEmployeeCredentialByEmployeeEmail(email);
        System.out.println("EmployeeCredential: " + employeeCredential.getEmployee());

        System.out.println("Password Hash: " + hashUtil.getHashedPassword(password));
        // Check if the email and password are valid
        if (employeeCredential != null
                && employeeCredential
                .getPasswordHash()
                .equals(hashUtil.getHashedPassword(password))) {
            // Generate a token (for simplicity, using email as token)
            try {
                String token = authServiceHelper.generateAndStoreToken(employeeCredential.getEmployee().getId());
                return LoginResponseDto.builder()
                        .token(token)
                        .build();
            }
            catch (Exception e) {
                throw new RuntimeException("Error generating token", e);
            }
        }
        return null;
    }


    @Override
    public void logout(String token) {

    }

    @Override
    public String authenticateAndGetEmployeeId(String token) {
        // Validate the token and extract employeeId
        AuthToken authToken = authServiceHelper.getAuthToken(token);
        if (authToken == null) {
            System.out.println("AuthToken is null");
            throw new AuthTokenExpiredException("Invalid or expired token");
        }

        // Check if the token is expired
        if (authToken.getExpiry().before(new Timestamp(System.currentTimeMillis()))) {
            throw new AuthTokenExpiredException("Token expired");
        }
        if (authToken.getExpiry().before(new Timestamp(System.currentTimeMillis() + 60 * 1000))) {
            authToken.setExpiry(new Timestamp(System.currentTimeMillis() + 60 *60 * 1000));
            authServiceHelper.setAuthToken(token, authToken);
        }
        return authToken.getEmployeeId();
    }
}
