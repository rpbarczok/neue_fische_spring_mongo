package org.example.mongodb.services;


import lombok.RequiredArgsConstructor;
import org.example.mongodb.dto.CharacterDTO;
import org.example.mongodb.exceptions.BadRequestException;
import org.example.mongodb.exceptions.NotFoundException;
import org.example.mongodb.repository.CharacterRepo;
import org.example.mongodb.models.Character;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepo characterRepo;
    private final IdService idService;

    public List<Character> getCharacters() {
        return characterRepo.findAll();
    }

    public List<Character> getCharacters(int age) {
        return characterRepo.getCharactersByAgeLessThanEqual(age);
    }

    public Character createCharacter(CharacterDTO characterDTO) throws BadRequestException {
        try {
            Character character = new Character(idService.randomId(), characterDTO.name(), characterDTO.age(), characterDTO.profession());
            try {
                return characterRepo.save(character);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Character getCharacterById(String id) {
        return characterRepo.getCharacterById(id);
    }

    public void deleteCharacter(String id) throws NotFoundException {
        Character character = characterRepo.getCharacterById(id);
        if (character != null) {
            characterRepo.deleteById(character.getId());
        } else {
            throw new NotFoundException("Character with the id " + id + " not Found.");
        }
    }

    public Character putCharacterById(Character character) throws NotFoundException {
        Character existingCharacter = characterRepo.getCharacterById(character.getId());
        if  (existingCharacter != null) {
           return characterRepo.save(existingCharacter);
        } else {
            throw new NotFoundException("Character with id " + character.getId() + " not Found.");
        }
    }
}
