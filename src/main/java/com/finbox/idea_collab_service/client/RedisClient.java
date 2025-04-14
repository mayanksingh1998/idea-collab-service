package com.finbox.idea_collab_service.client;

import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class RedisClient implements RedisCacheClient {

    private static final String REDIS_KEY_PREFIX = "idea-collab-services:";

    private final StatefulRedisMasterReplicaConnection<String, byte[]> connection;

    public RedisClient(final @NonNull StatefulRedisMasterReplicaConnection<String, byte[]> connection) {
        this.connection = connection;
    }

    @Override
    public void set(String key, byte[] value) {
        connection.sync().set(buildKey(key), value);
    }

    @Override
    public void setEx(String key, byte[] value, Long expiryInSec) {
        connection.sync().setex(buildKey(key), expiryInSec, value);
    }

    @Override
    public byte[] get(String key) {
        return connection.sync().get(buildKey(key));
    }

    @Override
    public Long incr(String key) {
        return connection.sync().incr(buildKey(key));
    }

    @Override
    public byte[] hashGet(String key, String internalMapKey) {
        return connection.sync().hget(buildKey(key), internalMapKey);
    }

    @Override
    public Map<String, byte[]> hashGetAll(String key) {
        return connection.sync().hgetall(buildKey(key));
    }

    @Override
    public void hashSet(String key, Map<String, byte[]> internalMap) {
        connection.sync().hset(buildKey(key), internalMap);
    }

    @Override
    public void hashSet(String key, String internalMapKey, byte[] value) {
        connection.sync().hset(buildKey(key), internalMapKey, value);
    }

    @Override
    public void setExpire(String key, Long expiryInSec) {
        connection.async().expire(buildKey(key), expiryInSec);
    }

    @Override
    public void setExpireAtInstant(String key, Instant expiryInstant) {
        connection.async().expireat(buildKey(key), expiryInstant);
    }

    private String buildKey(String key) {
        return REDIS_KEY_PREFIX + key;
    }

    @Override
    public Long incrBy(String key, Long value) {
        return connection.sync().incrby(buildKey(key), value);
    }

    @Override
    public void delete(String key) {
        connection.sync().del(buildKey(key));
    }

    @Override
    public void setAdd(String key, byte[] value) {
        connection.sync().sadd(buildKey(key), value);
    }

    @Override
    public boolean setIsMember(String key, byte[] value) {
        return connection.sync().sismember(buildKey(key), value);
    }
}
