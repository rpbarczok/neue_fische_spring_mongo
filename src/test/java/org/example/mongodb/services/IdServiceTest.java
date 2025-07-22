package org.example.mongodb.services;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class IdServiceTest {
    static UUID uuid =  UUID.randomUUID();

    @Test
    void randomId_gets_random_id () {
        mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(uuid);

        IdService idService = new IdService();

        String expected = uuid.toString();

        String actual = idService.randomId();

        assertEquals(expected, actual);
    }
}