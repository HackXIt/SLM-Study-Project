package at.fhtw.bic.slmstudyproject.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MessageController.class)
@SpringBootTest
public class MessageResetTests {
    
    @Autowired
    MockMvc mockMvc;
    
    
    
}