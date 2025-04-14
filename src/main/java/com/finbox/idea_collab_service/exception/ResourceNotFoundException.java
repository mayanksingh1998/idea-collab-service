package com.finbox.idea_collab_service.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7574595337888092275L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
