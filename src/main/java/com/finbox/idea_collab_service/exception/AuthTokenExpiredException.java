package com.finbox.idea_collab_service.exception;

public class AuthTokenExpiredException extends RuntimeException {
    private static final long serialVersionUID = 4634405881026635667L;

    public AuthTokenExpiredException(String message) {
        super(message);
    }

}
