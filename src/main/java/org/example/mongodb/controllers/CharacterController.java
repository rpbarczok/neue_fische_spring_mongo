package org.example.mongodb.controllers;
import org.example.mongodb.dto.CharacterDTO;
import org.example.mongodb.exceptions.NotFoundException;
import org.example.mongodb.services.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.mongodb.models.Character;
import org.springframework.web.server.ResponseStatusException;
import org.example.mongodb.exceptions.BadRequestException;

import java.util.List;

@RestController
@RequestMapping("/asterix/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public List<Character> getAllCharacters() {
        try {
            return characterService.getCharacters();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

    }

    @PostMapping
    public Character createCharacter(@RequestBody CharacterDTO characterDTO) {
        try {
            return characterService.createCharacter(characterDTO);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

    }

    @GetMapping("{id}")
    public Character getCharacterById(@PathVariable String id) {
        try {
            Character character = characterService.getCharacterById(id);
            if (character == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character Not Found");
            } else {
                return characterService.getCharacterById(id);
            }
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character Not Found");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        try {
            characterService.deleteCharacter(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public Character putCharacter(@RequestBody Character character) {

        try {
            return characterService.putCharacterById(character);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/age/let/{age}")
    public List<Character> getCharactersByAge(@PathVariable int age) {
        try {
            return characterService.getCharacters(age);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}
