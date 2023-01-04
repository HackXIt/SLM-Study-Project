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

    @GetMapping("")
    public ResponseEntity<String> Message() {
        if(currentApiMessage == null) {
            if (apiMessageDefault.isBlank()) {
                return new ResponseEntity<>("No default message or message set", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            return new ResponseEntity<>(apiMessageDefault, HttpStatus.OK);
            }
        return new ResponseEntity<>(currentApiMessage, HttpStatus.OK);
    }
    
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

    @GetMapping("/set")
    public ResponseEntity<String> SetMessage(@RequestParam(name="m", required=true) String msg){
        currentApiMessage = msg;
        return new ResponseEntity<>(currentApiMessage, HttpStatus.OK);
    }
    
}
