package org.example.mongodb.services;

import org.example.mongodb.dto.CharacterDTO;
import org.example.mongodb.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.example.mongodb.models.Character;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CharacterServiceTest {
    static Character asterix = new Character("1", "Asterix", 35,"Warrior");
    static Character asterixUpdated = new Character("1", "Asterix", 40,"Warrior");
    static Character obelix = new Character("2","Obelix", 35, "Supplier");
    static CharacterDTO newCharacter = new CharacterDTO("Falballa", 28, "CEO");
    static Character falballa = new Character ("3","Falballa", 28, "CEO");
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
        verify(mockCharacterRepo,times(1)).findAll();
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
        verify(mockCharacterRepo,times(1)).findAll();
    }

    @Test
    void createCharacter_returns_new_character() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockIdService.randomId()).thenReturn("3");
        when(mockCharacterRepo.save(falballa)).thenReturn(falballa);

        try {
            Character actual = characterService.createCharacter(newCharacter);
            assertEquals(actual,falballa);
            verify(mockCharacterRepo,times(1)).save(falballa);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void getCharacterById_returns_character() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockCharacterRepo.getCharacterById("1")).thenReturn(asterix);

        try {
            Character actual = characterService.getCharacterById("1");
            assertEquals(actual,asterix);
            verify(mockCharacterRepo,times(1)).getCharacterById("1");
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void getCharacterById_throws_error_when_character_does_not_exist() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockCharacterRepo.getCharacterById("1")).thenReturn(null);

        try {
            Character actual = characterService.getCharacterById("1");
            fail();
        } catch (Exception e) {
            verify(mockCharacterRepo,times(1)).getCharacterById("1");
            // success
        }
    }

    @Test
    void deleteCharacter_succeeds_with_existing_character() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.getCharacterById("1")).thenReturn(asterix);

        try {
            characterService.deleteCharacter("1");
            verify(mockCharacterRepo).deleteById("1");
        } catch (Exception e) {
            fail();
        }


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
            verify(mockCharacterRepo,times(1)).getCharacterById("6");
            // succeed
        }

    }

    @Test
    void putCharacterById_updates_character() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockCharacterRepo.getCharacterById("1")).thenReturn(asterix);
        when(mockCharacterRepo.save(asterixUpdated)).thenReturn(asterixUpdated);

        try {
            Character actual = characterService.putCharacterById(asterixUpdated);
            assertEquals(actual,asterixUpdated);
            verify(mockCharacterRepo,times(1)).getCharacterById("1");
            verify(mockCharacterRepo,times(1)).save(asterixUpdated);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void putCharacterById_throws_error_when_character_does_not_exist() {
        CharacterRepo mockCharacterRepo = Mockito.mock(CharacterRepo.class);
        IdService mockIdService = Mockito.mock(IdService.class);
        CharacterService characterService = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockCharacterRepo.getCharacterById("1")).thenReturn(null);

        try {
            Character actual = characterService.putCharacterById(asterixUpdated);
            fail();
        } catch (Exception e) {
            verify(mockCharacterRepo,times(1)).getCharacterById("1");
            // success
        }
    }
}