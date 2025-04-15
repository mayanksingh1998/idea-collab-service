package com.finbox.idea_collab_service.exception;

public class UserNotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = 3969652826364664212L;
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
