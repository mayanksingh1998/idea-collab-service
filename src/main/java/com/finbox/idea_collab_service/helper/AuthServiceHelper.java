package com.finbox.idea_collab_service.helper;

import com.finbox.idea_collab_service.client.RedisClient;
import com.finbox.idea_collab_service.client.serializer.RedisSerializer;
import com.finbox.idea_collab_service.dto.AuthToken;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AuthServiceHelper {

    private static final long TOKEN_EXPIRY_MINUTES = 60;

    private final RedisClient redisClient;

    private final RedisSerializer redisSerializer;


    public AuthServiceHelper(RedisClient redisClient, RedisSerializer redisSerializer) {
        this.redisClient = redisClient;
        this.redisSerializer = redisSerializer;
    }

    public String generateAndStoreToken(String employeeId) {
        String token = UUID.randomUUID().toString();
        Timestamp expiryTime = Timestamp.valueOf(LocalDateTime.now().plus(Duration.ofMinutes(TOKEN_EXPIRY_MINUTES)));
        AuthToken authToken = new AuthToken(employeeId, token, expiryTime);
        byte[] authTokenInBytes = redisSerializer.serialize(authToken);
        redisClient.set(token, authTokenInBytes);
        return token;
    }

    public AuthToken getAuthToken(String token) {
        byte[] authTokenInBytes = redisClient.get(token);
        if (authTokenInBytes != null) {
            return redisSerializer.deserialize(authTokenInBytes, AuthToken.class);
        }
        return null;
    }

    public AuthToken setAuthToken(String token, AuthToken authToken) {
        byte[] authTokenInBytes = redisSerializer.serialize(authToken);
        redisClient.set(token, authTokenInBytes);
        return authToken;
    }
}
