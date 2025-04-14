package com.finbox.idea_collab_service.client;

import java.time.Instant;
import java.util.Map;

public interface RedisCacheClient {
    void set(String key, byte[] value);

    void setEx(String key, byte[] value, Long expiryInSec);

    byte[] get(String key);

    Long incr(String key);

    byte[] hashGet(String key, String internalMapKey);

    Map<String, byte[]> hashGetAll(String key);

    void hashSet(String key, Map<String, byte[]> internalMap);

    void hashSet(String key, String internalMapKey, byte[] value);

    void setExpire(String key, Long expiryInSec);

    void setExpireAtInstant(String key, Instant expiryInstant);

    Long incrBy(String key, Long value);

    void delete(String key);

    void setAdd(String key, byte[] value);

    boolean setIsMember(String key, byte[] value);
}
