package com.finbox.idea_collab_service.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashUtilTest {

    private final HashUtil hashUtil = new HashUtil();

    @Test
    void testGetHashedPassword_ReturnsConsistentHash() {
        String password = "secret123";

        String hash1 = hashUtil.getHashedPassword(password);
        String hash2 = hashUtil.getHashedPassword(password);

        assertNotNull(hash1);
        assertEquals(hash1, hash2); // should be deterministic
    }

    @Test
    void testGetHashedPassword_ReturnsDifferentHashForDifferentInput() {
        String hash1 = hashUtil.getHashedPassword("password1");
        String hash2 = hashUtil.getHashedPassword("password2");

        assertNotEquals(hash1, hash2); // hashes of different strings must differ
    }

    @Test
    void testGetHashedPassword_ThrowsExceptionForNullInput() {
        assertThrows(NullPointerException.class, () -> {
            hashUtil.getHashedPassword(null);
        });
    }
}

