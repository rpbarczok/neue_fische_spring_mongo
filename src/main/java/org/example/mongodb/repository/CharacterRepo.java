package org.example.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.example.mongodb.models.Character;

@Repository
public interface CharacterRepo extends MongoRepository<Character,String> {


}
