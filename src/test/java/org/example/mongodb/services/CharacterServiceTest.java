package org.example.mongodb.services;

import org.example.mongodb.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.example.mongodb.models.Character;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CharacterServiceTest {
    static Character asterix = new Character("1", "Asterix", 35,"Warrior");
    static Character obelix = new Character("2","Obelix", 35, "Supplier");
    static List<Character> characters = List.of(asterix,obelix);
    @Test
    void getCharacters() {
        // create Service with mocking
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockCharacterRepo.findAll()).thenReturn(characters);
        // When
        List<Character> actual = characterService.getCharacters();
        //Then
        assertEquals(actual,characters);

    }

    @Test
    void getCharacterById() {
    }

    @Test
    void deleteCharacter() {
    }

    @Test
    void putCharacterById() {
    }
}