# Testing

Since our development process was TDD (**<u>t</u>**est-**<u>d</u>**riven-**<u>d</u>**evelopment), tests were implemented before any implementation. To explain this process of development in detail, an [example is provided further below](#process-example), which dives into the development of the branch `feature/api-message-reset`.

In addition to our process, we needed to implement **<u>c</u>**ontinous **<u>i</u>**ntegration (CI) and **<u>c</u>**ontinous **<u>d</u>**eployment (CD) pipelines. Those pipelines used the default maven commands, to build our project for artifacts and execute the available unit tests on them.
The details about that, are in our CI/CD documentation.

## <a name="process-example"></a> Example process in feature branch

To implement a test, we used the testing features of the [spring boot framework](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html).
Initially a test class looks like the following example:

![image-20221125170335458](.\attachments\image-20221125170335458.png)

As intended in TDD, even this simple test class will initially fail:

![MessageControllerClass_build-error.jpg](.\attachments\MessageControllerClass_build-error.jpg)

The process of TDD dictates, that we now refactor our code to fix this initial error:

![MessageControllerClass_build-fix.jpg](.\attachments\MessageControllerClass_build-fix.jpg)

![MessageControllerClass_build-fix2.jpg](.\attachments\MessageControllerClass_build-fix2.jpg)

![MessageControllerClass_git-commits.jpg](.\attachments\MessageControllerClass_git-commits.jpg)

Great! 

But we still don't have any relevant tests, which were implemented using the same principle as before:

1. Implement a test
2. Execute test and expect failure
3. Refactor code until Expectation succeeds.
4. Repeat.

To help us implement our testcases for the actual API paths, we required some additional help of the Spring Framework, since we need a client to make API requests.
For this we used the provided `MockMvc`, which is a complete `mocking` client to do web requests. 
The client is very useful, since it provides mechanism to `assert` and `expect` results from the reponse of the API, which allowed us to design our API with TDD as process.

![MockMvc_usage-explanation.jpg](.\attachments\MockMvc_usage-explanation.jpg)

In the feature branch, the process was continued until all requirements of the feature were implemented and commited. 

For our example feature `feature/api-message-reset`, our resulting tests were: (`MessageResetTests`@`f91fff5`)

```java
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
```

And here is the final implementation of these tests: (`MessageController.java` @ `f91fff5`)

```java
package at.fhtw.bic.slmstudyproject.controller;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    
    private String apiMessageDefault = "Status Ok";
    private String currentApiMessage;
    
    @GetMapping("/reset")
    public ResponseEntity<String> MessageReset() {
        if(apiMessageDefault.isBlank()) {
            return new ResponseEntity<>("Default message is not set.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        currentApiMessage = apiMessageDefault;
        return new ResponseEntity<>(currentApiMessage, HttpStatus.OK);
    }
    
    @GetMapping("/default")
    public ResponseEntity<String> SetMessageDefault(@RequestParam(required=true) String msg) {
        apiMessageDefault = msg;
        return new ResponseEntity<>(apiMessageDefault, HttpStatus.OK);
    }
    
}
```
