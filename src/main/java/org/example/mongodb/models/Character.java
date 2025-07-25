package org.example.mongodb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class Character {
    private String id;
    private String name;
    private int age;
    private String profession;
}
