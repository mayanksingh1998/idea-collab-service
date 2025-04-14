package com.finbox.idea_collab_service.controller;

import com.finbox.idea_collab_service.dto.reponse.IdeaColabSvcResponse;
import com.finbox.idea_collab_service.dto.reponse.LoginResponseDto;
import com.finbox.idea_collab_service.dto.request.LoginDto;
import com.finbox.idea_collab_service.service.AuthService;
import com.finbox.idea_collab_service.utils.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth/employee/login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaColabSvcResponse<LoginResponseDto>> login(
            @RequestBody LoginDto loginDto) {
        return ResponseBuilder.build(
                authService.authenticate(loginDto.getEmail(), loginDto.getPassword()), HttpStatus.OK);
    }
}
