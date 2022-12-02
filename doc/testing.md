# Testing

Since our development process was TDD (<b><u>t</u></b>est-<b><u>d</u></b>riven-<b><u>d</u></b>evelopment), tests were implemented before any implementation. To explain this process of development in detail, an [example is provided further below](#process-example), which dives into the development of the branch `feature/api-message-reset`.

In addition to our process, we needed to implement <b><u>c</u></b>ontinous <b><u>i</u></b>ntegration (CI) and <b><u>c</u></b>ontinous <b><u>d</u></b>eployment (CD) pipelines. Those pipelines used the default maven commands, to build our project for artifacts and execute the available unit tests on them.
The details about that, are in our CI/CD documentation.

In the following sections, we document what our unit tests do. The sections are seperated by feature branch, meaning each section contains a different API path with different corresponding testcases.

Since the internal state of the application stays the same throughout test execution, we needed to set the annotation `@DirtiesContext` for some testcases, otherwise one test could affect the expected initial state of another.

# feature/api-message

The `message` feature returns either the current service message or the default service message, if the `current` message hasn't been set.

## GetMessageTest

This testcase verifies the existance and functionality of the APi-path `api/message`.
On initial start of the webpage, the service doesn't have an initialized `message`, which is why we provide a default message.
The initial default message is statically set to `Status OK`.

We expect a positive response `200` of the API call and verify the contents of the response against our expected static message.

```java
@Test
@Order(1)
void GetMessageTest() throws Exception {
    mockMvc.perform(get("/api/message"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().string("Status Ok"));
}
```

## GetMessageWithoutDefaultTest

This testcase verifies the expected failure of `api/message`.

We expect a failure of `api/message` when the `default` message was set to a blank message, without having called `api/message/set` to initialize the `current` message.

This failure prevents a segmentation fault, because when trying to return a `null` current message, it would mean we have an access violation.

```java
@Test
@Order(2)
// Makes context dirty because: defaultMessage will be blank
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
void GetMessageWithoutDefaultTest() throws Exception {
    mockMvc.perform(get("/api/message/default?msg="))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().string(""));
    mockMvc.perform(get("/api/message"))
            .andExpect(status().is5xxServerError())
            .andExpect(content().string("No default message or message set"));
}
```



# feature/api-message-set

# feature/api-message-reset

The `reset` feature is supposed to **reset** the current message to a <u>default message</u> of the server.
We adapted the requirement a little bit, which is why we also added an API path for a `default` message, so we can make sure that there's a message to begin with, without requiring the implementation of `set`.

## GetMessageResetTest

This testcase verifies the existence and functionality of the API-path `/api/message/reset` by expecting a response of `200` when calling it with the `MockMvc` client.

By checking the response, we successfully tested the functionality of `reset`, since that's what it responds with.

This test is executed **first**.
It affects the internal state of `currentApiMessage` by setting it to the content of `apiMessageDefault`.

```java
@Test
@Order(1)
// Makes context dirty because: currentMessage will be set to "Status Ok"
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
void GetMessageResetTest() throws Exception {
    mockMvc.perform(get("/api/message/reset"))
            .andExpect(status().is2xxSuccessful());
}
```

## GetMessageDefaultTest

This testcase verifies the `default` API-path functionality.

It sets the default message, verifies the response of the API call and then verifies the contents of the response.
After successful execution of this path, it resets the default message to the original state and verifies the result.

This test is executed **second**.
It affects the internal state of `apiMessageDefault` by setting it to a blank string.

```java
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
```

## GetMessageResetWithoutDefaultTest

This testcase verifies the expected failure of the `reset` API-path.

When `reset` is called without a `default` message, an internal server error occurs, since it requires the default message to be set.
We defined this testcase, to make sure that the implementation will use the default message for calling `reset`, because we defined that `reset` doesn't clear the message, but instead resets the message to a default one. Without a default message, this API cannot be called.

This test is executed **third**.
It affects the internal state of `apiMessageDefault` by setting it to a blank string.

```java
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
```

## <a name="process-example"></a> Example process in feature branch

To implement a test, we used the testing features of the [spring boot framework](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html).
Initially a test class looks like the following example:

![image-20221125170335458](https://github.com/HackXIt/SLM-Study-Project/blob/documentation/doc/attachments/image-20221125170335458.png)

As intended in TDD, even this simple test class will initially fail:

![MessageControllerClass_build-error.jpg](https://github.com/HackXIt/SLM-Study-Project/blob/documentation/doc/attachments/MessageControllerClass_build-error.jpg)

The process of TDD dictates, that we now refactor our code to fix this initial error:

![MessageControllerClass_build-fix.jpg](https://github.com/HackXIt/SLM-Study-Project/blob/documentation/doc/attachments/MessageControllerClass_build-fix.jpg)

![MessageControllerClass_build-fix2.jpg](https://github.com/HackXIt/SLM-Study-Project/blob/documentation/doc/attachments/MessageControllerClass_build-fix2.jpg)

![MessageControllerClass_git-commits.jpg](https://github.com/HackXIt/SLM-Study-Project/blob/documentation/doc/attachments/MessageControllerClass_git-commits.jpg)

Great! 

But we still don't have any relevant tests, which were implemented using the same principle as before:

1. Implement a test
2. Execute test and expect failure
3. Refactor code until Expectation succeeds.
4. Repeat.

To help us implement our testcases for the actual API paths, we required some additional help of the Spring Framework, since we need a client to make API requests.
For this we used the provided `MockMvc`, which is a complete `mocking` client to do web requests. 
The client is very useful, since it provides mechanism to `assert` and `expect` results from the reponse of the API, which allowed us to design our API with TDD as process.

![MockMvc_usage-explanation.jpg](https://github.com/HackXIt/SLM-Study-Project/blob/documentation/doc/attachments/MockMvc_usage-explanation.jpg)

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
