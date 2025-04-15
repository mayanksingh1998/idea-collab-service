package com.finbox.idea_collab_service.exception.handler;


import com.finbox.idea_collab_service.exception.*;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UserNotAllowedForVoteException.class})
    public ResponseEntity<Object> handleUserNotAllowedForVote(UserNotAllowedForVoteException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(value = {AuthTokenExpiredException.class})
    public ResponseEntity<Object> handleAuthTokenExpired(AuthTokenExpiredException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, BadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleBUserNotFoundException(Exception ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(value = {InvalidIdeaReactionException.class})
    public ResponseEntity<Object> handleInvalidIdeaReactionException(Exception ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {IdeaNotActiveException.class})
    public ResponseEntity<Object> handleIdeaNotActiveException(Exception ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {UserNotAuthorizedException.class})
    public ResponseEntity<Object> handleUserNotAuthorizedException(Exception ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", status.value());
        errorBody.put("error", status.getReasonPhrase());
        errorBody.put("message", message);

        return new ResponseEntity<>(errorBody, status);
    }
}

