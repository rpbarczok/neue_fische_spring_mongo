package org.example.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.example.mongodb.models.Character;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepo extends MongoRepository<Character,String> {

    Character getCharacterById(String id);

    List<Character> getCharactersByAgeLessThanEqual(int age);
}
