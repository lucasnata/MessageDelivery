package br.com.luiza.labs.messagedeliveryspring.app.controller;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.services.MessageService;
import br.com.luiza.labs.messagedeliveryspring.app.services.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    RecipientService recipientService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMessage(@RequestBody MessageDTO messageDTO) {
        messageService.addMessage(messageDTO);
    }

    @GetMapping
    public String getMessages() {
        return "Mensagens";
    }
}
