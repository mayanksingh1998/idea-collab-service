package com.finbox.idea_collab_service.service;

import com.finbox.idea_collab_service.dto.reponse.LoginResponseDto;

public interface AuthService {
    LoginResponseDto authenticate(String username, String password);

    void logout(String token);


    String authenticateAndGetEmployeeId(String token);


}
