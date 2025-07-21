package org.example.mongodb.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Character {
    private String id;
    private String name;
    private int age;
    private String profession;

}
