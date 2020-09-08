package br.com.luiza.labs.messagedeliveryspring.app.controller;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageStatusDTO;
import br.com.luiza.labs.messagedeliveryspring.app.mappers.MessageMapper;
import br.com.luiza.labs.messagedeliveryspring.app.services.MessageService;
import br.com.luiza.labs.messagedeliveryspring.app.services.RecipientService;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
public class MessageController extends GenericController{

    @Autowired
    MessageService messageService;

    @Autowired
    RecipientService recipientService;

    private final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @PostMapping(produces = JSON, consumes = JSON)
    public ResponseEntity<MessageDTO> addMessage(@RequestBody @Valid MessageDTO messageDTO) {
        Optional<Message> message = messageService.addMessage(messageDTO);
        return new ResponseEntity<>(MessageMapper.messageEntitytoDTO(message), HttpStatus.CREATED);
    }

    @GetMapping(produces = JSON, value = "/{id}/status")
    public ResponseEntity<MessageStatusDTO> getMenssage(@PathVariable BigInteger id) {
        return messageService.getMessage(id)
                .map(m -> MessageMapper.messageEntitytoMessageStatusDTO(m))
                .map(this::okResponse)
                .orElseGet(this::notFoundResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MessageStatusDTO> deleteMessage(@PathVariable BigInteger id) {
        return messageService.deleteMessage(id)
                .map(m -> MessageMapper.messageEntitytoMessageStatusDTO(m))
                .map(this::okResponse)
                .orElseGet(this::notFoundResponse);
    }

    @GetMapping(produces = JSON)
    public List<MessageDTO> getMessages() {
        return messageService.getMessages().stream()
                .map(m -> MessageMapper.messageEntitytoDTO(Optional.of(m))).collect(Collectors.toList());
    }
}
