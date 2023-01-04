package at.fhtw.bic.slmstudyproject.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MessageController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MessageResetTests {
    
    @Autowired
    MockMvc mockMvc;
    
    // Using test order, since when GetMessageResetTest is run between, it will fail,
    // since Initial Default Message was already reset

    // @DirtiesContext IS ESSENTIAL, otherwise testcases relying on initial state for current message will fail
    
    @Test
    @Order(1)
    // Makes context dirty because: currentMessage will be set to "Status Ok"
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void GetMessageResetTest() throws Exception {
        mockMvc.perform(get("/api/message/reset"))
                .andExpect(status().is2xxSuccessful());
    }
    
    @Test
    @Order(2)
    // Makes context dirty because: defaultMessage will be blank
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void GetMessageDefaultTest() throws Exception {
        mockMvc.perform(get("/api/message/default?msg=Hello"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello"));
        mockMvc.perform(get("/api/message/default?msg="))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(""));
    }
    
    @Test
    @Order(3)
    // Makes context dirty because: defaultMessage will be blank
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void GetMessageResetWithoutDefaultTest() throws Exception {
        mockMvc.perform(get("/api/message/default?msg="))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(""));
        mockMvc.perform(get("/api/message/reset"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Default message is not set."));
    }
    
}
