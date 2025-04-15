package com.finbox.idea_collab_service.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8157941669383136807L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
