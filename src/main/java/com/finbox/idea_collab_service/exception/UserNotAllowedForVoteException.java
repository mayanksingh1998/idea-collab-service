package com.finbox.idea_collab_service.exception;

public class UserNotAllowedForVoteException extends RuntimeException {
    private static final long serialVersionUID = 5918716115452693442L;

    public UserNotAllowedForVoteException(String message) {
        super(message);
    }
}
