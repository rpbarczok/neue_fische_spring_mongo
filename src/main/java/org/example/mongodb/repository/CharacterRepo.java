package org.example.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.example.mongodb.models.Character;

import java.util.List;

@Repository
public interface CharacterRepo extends MongoRepository<Character,String> {
    
    List<Character> getCharactersByName(String name);

    Character getCharacterById(String id);

    Character getCharactersByProfession(String profession);

    Character getCharactersByAge(int age);

}
