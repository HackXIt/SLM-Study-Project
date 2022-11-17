package at.fhtw.bic.slmstudyproject.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MessageController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MessageTests {
    
    @Autowired
    MockMvc mockMvc;
    
    @Test
    @Order(1)
    void GetMessageTest() throws Exception {
        mockMvc.perform(get("/api/message"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> result.getResponse().getContentAsString().contentEquals("Status Ok"));
    }

    @Test
    @Order(2)
    void GetMessageWithoutDefaultTest() throws Exception {
        mockMvc.perform(get("/api/message/default?msg="))
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> result.getResponse().getContentAsString().isBlank());
        mockMvc.perform(get("/api/message"))
                .andExpect(status().is5xxServerError())
                .andExpect(result -> result.getResponse().getContentAsString().contentEquals("No default message or message set"));
    }
    
}
