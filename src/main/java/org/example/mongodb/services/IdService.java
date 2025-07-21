package org.example.mongodb.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record IdService () {

    public String randomId () {
        return UUID.randomUUID().toString();
    }
}
