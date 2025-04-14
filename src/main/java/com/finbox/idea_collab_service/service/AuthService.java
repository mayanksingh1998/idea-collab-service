package com.finbox.idea_collab_service.service;

import com.finbox.idea_collab_service.dto.reponse.LoginResponseDto;

public interface AuthService {
    LoginResponseDto authenticate(String username, String password);

    String register(String username, String password, String email);

    void logout(String token);

    boolean isAuthenticated(String token);

    String getUsernameFromToken(String token);

    String authenticateAndGetEmployeeId(String token);


}
