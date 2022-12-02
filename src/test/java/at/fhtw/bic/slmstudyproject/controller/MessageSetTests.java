package at.fhtw.bic.slmstudyproject.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MessageController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MessageSetTests {
    
    @Autowired
    MockMvc mockMvc;
    
    @Test
    @Order(1)
    void GetMessageSetTest() throws Exception {
        mockMvc.perform(get("/api/message/set?m=Giraffe"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Giraffe"));
    }
    
    @Test
    @Order(2)
    void GetMessageSetMultipleWordsTest() throws Exception {
        mockMvc.perform(get("/api/message/set?m=The giraffe is a large African hoofed mammal belonging to the genus Giraffa"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("The giraffe is a large African hoofed mammal belonging to the genus Giraffa"));
    }
}
