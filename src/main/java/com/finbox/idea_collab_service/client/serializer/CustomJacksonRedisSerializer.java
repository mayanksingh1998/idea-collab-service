package com.finbox.idea_collab_service.client.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finbox.idea_collab_service.client.exception.CacheSerializationDeSerializationException;

public class CustomJacksonRedisSerializer implements RedisSerializer {

    private final ObjectMapper objectMapper;

    public CustomJacksonRedisSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> byte[] serialize(final T cacheObjectClass) throws CacheSerializationDeSerializationException {
        try {
            if (cacheObjectClass != null) {
                return objectMapper.writeValueAsBytes(cacheObjectClass);
            } else {
                throw new CacheSerializationDeSerializationException("Class instance is invalid");
            }
        } catch (Exception e) {
            throw new CacheSerializationDeSerializationException(
                    "Exception while serializing object to cacheValue in bytes ### " + e.getMessage(), e);
        }
    }

    @Override
    public <T> T deserialize(byte[] value, Class<T> cacheObjectClass)
            throws CacheSerializationDeSerializationException {
        try {
            if (value != null && cacheObjectClass != null) {
                return objectMapper.readValue(value, cacheObjectClass);
            } else {
                throw new CacheSerializationDeSerializationException("Value, or class instance is invalid");
            }
        } catch (Exception e) {
            throw new CacheSerializationDeSerializationException(
                    "Exception while de serializing cacheValue in bytes to object ### " + e.getMessage(), e);
        }
    }
}
