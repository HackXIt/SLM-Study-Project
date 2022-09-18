package at.fhtw.bic.slmstudyproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SlmStudyProjectApplicationTests {

    @Autowired
    MockMvc mockMvc;
    
    @Test
    void getUptimeMinutes() {
    }
    
    @Test
    void getUptimeHours() throws Exception {
        mockMvc.perform(get("/uptime/hours"))
                .andExpect(status().is(200))
                .andExpect(content().contentType("double"));
    }

}
