package com.finbox.idea_collab_service.exception;

public class IdeaNotActiveException extends RuntimeException {
    private static final long serialVersionUID = 1790774920131294795L;

    public IdeaNotActiveException(String message) {
        super(message);
    }

}