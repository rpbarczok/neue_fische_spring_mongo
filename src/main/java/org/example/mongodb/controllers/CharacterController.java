package org.example.mongodb.controllers;

import jakarta.websocket.server.PathParam;
import org.example.mongodb.repository.CharacterRepo;
import org.springframework.web.bind.annotation.*;
import org.example.mongodb.models.Character;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/asterix/characters")
public class CharacterController {

    private final CharacterRepo characterRepo;

    public CharacterController(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    @GetMapping
    public List<Character> getCharacters() {
        return characterRepo.findAll();
    }

    @PostMapping
    public Character createCharacter(@RequestBody String value) {
        String uuid = UUID.randomUUID().toString();
        String[] values = value.split("&");
        Character character = new Character(uuid, values[0].split("=")[1], Integer.parseInt(values[1].split("=")[1]), values[2].split("=")[1]);
        return characterRepo.save(character);
    }

    @GetMapping("{id}")
    public Character getCharacterById(@PathVariable String id) {
        return characterRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteCharacter(@PathVariable String id) {
        characterRepo.deleteById(id);
        return "successfully deleted";
    }
}
