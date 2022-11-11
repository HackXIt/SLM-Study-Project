package at.fhtw.bic.slmstudyproject.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MessageController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MessageResetTests {
    
    @Autowired
    MockMvc mockMvc;
    
    // Using test order, since when GetMessageResetTest is run between, it will fail,
    // since Initial Default Message was already reset
    
    @Test
    @Order(1)
    void GetMessageResetTest() throws Exception {
        mockMvc.perform(get("/api/message/reset"))
                .andExpect(status().is2xxSuccessful());
    }
    
    @Test
    @Order(2)
    void GetMessageDefaultTest() throws Exception {
        mockMvc.perform(get("/api/message/default?msg=Hello"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> result.getResponse().getContentAsString().contentEquals("Hello"));
        mockMvc.perform(get("/api/message/default?msg="))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> result.getResponse().getContentAsString().isBlank());
    }
    
    @Test
    @Order(3)
    void GetMessageResetWithoutDefaultTest() throws Exception {
        mockMvc.perform(get("/api/message/default?msg="))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> result.getResponse().getContentAsString().isBlank());
        mockMvc.perform(get("/api/message/reset"))
                .andExpect(status().is5xxServerError())
                .andExpect(result -> result.getResponse().getContentAsString().contentEquals("Default message is not set."));
                
    }
    
}
