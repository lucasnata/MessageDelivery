package br.com.luiza.labs.messagedeliveryspring.app.controller;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageStatusDTO;
import br.com.luiza.labs.messagedeliveryspring.app.mappers.MessageMapper;
import br.com.luiza.labs.messagedeliveryspring.app.services.IMessageService;
import br.com.luiza.labs.messagedeliveryspring.app.services.IRecipientService;
import br.com.luiza.labs.messagedeliveryspring.app.validations.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/message")
@Api(tags="Message Controller")
@AllArgsConstructor
public class MessageController extends GenericController{

    @Autowired
    IMessageService messageService;

    @Autowired
    IRecipientService recipientService;

    static final String PATH = "/message";

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Serializable> addMessage(@RequestBody @Valid MessageDTO messageDTO, UriComponentsBuilder uriComponentsBuilder) {
        ValidationFactory validationFactory = new ValidationFactory(messageDTO);
        if (validationFactory.haveNoErrors()) {
            return messageService.addMessage(messageDTO)
                    .map(m -> MessageMapper.messageEntitytoMessageStatusDTO(m))
                    .map(m -> this.componentBuilder(uriComponentsBuilder, m.getId(), PATH+"/{id}/status"))
                    .map(uri -> this.createdResponse(uri))
                    .orElseGet(this::badRequestResponse);
        } else {
            return this.badRequestResponse(validationFactory.getErrors());
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}/status")
    public ResponseEntity<MessageStatusDTO> getMessage(@PathVariable BigInteger id) {
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

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessages() {
        return messageService.getMessages().stream()
                .map(m -> MessageMapper.messageEntitytoDTO(m)).collect(Collectors.toList());
    }
}
