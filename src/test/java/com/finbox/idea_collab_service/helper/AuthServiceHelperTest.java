package com.finbox.idea_collab_service.helper;

import com.finbox.idea_collab_service.client.RedisClient;
import com.finbox.idea_collab_service.client.serializer.RedisSerializer;
import com.finbox.idea_collab_service.dto.AuthToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceHelperTest {

    private RedisClient redisClient;
    private RedisSerializer redisSerializer;
    private AuthServiceHelper authServiceHelper;

    @BeforeEach
    void setUp() {
        redisClient = mock(RedisClient.class);
        redisSerializer = mock(RedisSerializer.class);
        authServiceHelper = new AuthServiceHelper(redisClient, redisSerializer);
    }

    @Test
    void testGenerateAndStoreToken_ShouldGenerateTokenAndStoreInRedis() {
        String employeeId = "emp123";

        // Mock serializer behavior
        when(redisSerializer.serialize(any(AuthToken.class))).thenReturn("dummyBytes".getBytes());

        String token = authServiceHelper.generateAndStoreToken(employeeId);

        assertNotNull(token);
        verify(redisClient, times(1)).set(eq(token), any(byte[].class));
    }

    @Test
    void testGetAuthToken_ShouldReturnDeserializedAuthToken() {
        String token = UUID.randomUUID().toString();
        byte[] dummyBytes = "dummyBytes".getBytes();
        AuthToken expectedAuthToken = new AuthToken("emp123", token, Timestamp.valueOf(LocalDateTime.now().plusMinutes(60)));

        when(redisClient.get(token)).thenReturn(dummyBytes);
        when(redisSerializer.deserialize(dummyBytes, AuthToken.class)).thenReturn(expectedAuthToken);

        AuthToken result = authServiceHelper.getAuthToken(token);

        assertNotNull(result);
        assertEquals(expectedAuthToken, result);
    }

    @Test
    void testGetAuthToken_WhenRedisReturnsNull_ShouldReturnNull() {
        String token = "someToken";
        when(redisClient.get(token)).thenReturn(null);

        AuthToken result = authServiceHelper.getAuthToken(token);

        assertNull(result);
    }

    @Test
    void testSetAuthToken_ShouldStoreSerializedAuthTokenInRedis() {
        String token = UUID.randomUUID().toString();
        AuthToken authToken = new AuthToken("emp123", token, Timestamp.valueOf(LocalDateTime.now().plusMinutes(60)));
        byte[] serialized = "serialized".getBytes();

        when(redisSerializer.serialize(authToken)).thenReturn(serialized);

        AuthToken result = authServiceHelper.setAuthToken(token, authToken);

        assertEquals(authToken, result);
        verify(redisClient, times(1)).set(token, serialized);
    }
}

