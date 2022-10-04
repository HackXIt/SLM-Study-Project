package at.fhtw.bic.slmstudyproject.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MessageController.class)
public class MessageResetTests {
    
    @Autowired
    MockMvc mockMvc;
    
    @Test
    void MessageResetTest() throws Exception {
        mockMvc.perform(get("/api/message/reset"))
                .andExpect(status().is2xxSuccessful());
    }
    
}
