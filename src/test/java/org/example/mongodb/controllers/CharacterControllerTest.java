package org.example.mongodb.controllers;
import org.example.mongodb.models.Character;
import org.example.mongodb.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CharacterControllerTest {
    static Character asterix = new Character("1", "Asterix", 35,"Warrior");
    static Character obelix = new Character("2","Obelix", 35, "Supplier");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepo characterRepo;

    @Test
    void getAllCharacters_returns_character() throws Exception {
        //Given
        characterRepo.save(asterix);
        characterRepo.save(obelix);
        //When
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                          [
                            {
                              "id": "1",
                              "name": "Asterix",
                              "age": 35,
                              "profession": "Warrior"
                            },
                            {
                              "id": "2",
                              "name": "Obelix",
                              "age": 35,
                              "profession": "Supplier"
                            }
                          ]
                          """));
    }

    @Test
    void createCharacter_creates_and_returns_returns_character() throws Exception {
        //When
        mockMvc.perform(MockMvcRequestBuilders.post("/asterix/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Falballa",
                          "age": 28,
                          "profession": "CEO"
                        }
                        """))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                            {
                              "name": "Falballa",
                              "age": 28,
                              "profession": "CEO"
                            }
                          """))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }
}