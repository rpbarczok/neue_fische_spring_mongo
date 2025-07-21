package org.example.mongodb.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.mongodb.models.Character;
import java.util.List;

@RestController
@RequestMapping("/asterix/characters")
public class CharacterController {

    @GetMapping
    public List<Character> getCharacters() {
        return List.of(
                new Character("1", "Asterix", 35, "Warrior"),
                new Character("2", "Obelix", 35, "Supplier"),
                new Character("3", "Miraculix", 60, "Druid"),
                new Character("4", "Majestix", 60, "Chief"),
                new Character("5", "Troubadix", 25, "Bard"),
                new Character("6", "Gutemine", 35, "Chiefs Wife"),
                new Character("7", "Idefix", 5, "Dog"),
                new Character("8", "Geriatrix", 70, "Retiree"),
                new Character("9", "Automatix", 35, "Smith"),
                new Character("10", "Grockelix", 35, "Fisherman")
        );
    }
}
