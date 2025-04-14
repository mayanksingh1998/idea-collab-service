package com.finbox.idea_collab_service.client.exception;

public class CacheSerializationDeSerializationException extends RuntimeException {
    private static final long serialVersionUID = -5529070141768536094L;

    public CacheSerializationDeSerializationException(String message) {
        super(message);
    }

    public CacheSerializationDeSerializationException(String message, Throwable e) {
        super(message, e);
    }
}
