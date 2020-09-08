package br.com.luiza.labs.messagedeliveryspring.app.controller;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageStatusDTO;
import org.springframework.http.ResponseEntity;

public class GenericController {
    ResponseEntity<MessageStatusDTO> okResponse(MessageStatusDTO messageDTO) { return ResponseEntity.ok(messageDTO);}
    ResponseEntity<MessageStatusDTO> notFoundResponse() {return ResponseEntity.notFound().build();}
}
