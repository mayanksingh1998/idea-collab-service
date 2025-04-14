package com.finbox.idea_collab_service.client.serializer;

public interface RedisSerializer {
    public <T> byte[] serialize(final T cacheObjectClass);

    public <T> T deserialize(final byte[] value, final Class<T> cacheObjectClass);
}
