package org.example.mongodb.services;

import org.example.mongodb.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.example.mongodb.models.Character;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CharacterServiceTest {
    static Character asterix = new Character("1", "Asterix", 35,"Warrior");
    static Character obelix = new Character("2","Obelix", 35, "Supplier");
    static List<Character> characters = List.of(asterix,obelix);
    static List<Character> emptyList = List.of();
    @Test
    void getCharacters_returns_list_of_characters() {
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
    void getCharacters_returns_empty_list() {
        // create Service with mocking
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockCharacterRepo.findAll()).thenReturn(emptyList);
        // When
        List<Character> actual = characterService.getCharacters();
        //Then
        assertEquals(actual,emptyList);
    }

    @Test
    void getCharacterById() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockCharacterRepo.getCharacterById("1")).thenReturn(asterix);

        Character actual = characterService.getCharacterById("1");

        assertEquals(actual,asterix);

    }

    @Test
    void deleteCharacter_succeeds_with_existing_character() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.getCharacterById("1")).thenReturn(asterix);

        try {
            characterService.deleteCharacter("1");
        } catch (Exception e) {
            fail();
        }

        verify(mockCharacterRepo).deleteById("1");
    }

    @Test
    void deleteCharacter_succeeds_with_none_existing_character() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.getCharacterById("6")).thenReturn(null);

        try {
            characterService.deleteCharacter("6");
            fail();
        } catch (Exception e) {
            // succeed
        }

    }

    @Test
    void putCharacterById_updates_character() {

    }
}