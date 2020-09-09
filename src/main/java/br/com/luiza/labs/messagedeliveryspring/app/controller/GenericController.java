package br.com.luiza.labs.messagedeliveryspring.app.controller;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageStatusDTO;
import br.com.luiza.labs.messagedeliveryspring.app.validations.ErrorList;
import br.com.luiza.labs.messagedeliveryspring.app.validations.ErrorObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

public abstract class GenericController {
    ResponseEntity<MessageStatusDTO> okResponse(MessageStatusDTO messageDTO) { return ResponseEntity.ok(messageDTO);}
    ResponseEntity<MessageStatusDTO> notFoundResponse() {return ResponseEntity.notFound().build();}
    ResponseEntity<Serializable> createdResponse(final URI uri) {return ResponseEntity.created(uri).build();}
    ResponseEntity<Serializable> badRequestResponse(final List<ErrorObject> errors) {
        return ResponseEntity.badRequest().body(ErrorList.builder().errors(errors).build());
    }
    ResponseEntity<Serializable> badRequestResponse() { return ResponseEntity.badRequest().build();}

    URI componentBuilder(final UriComponentsBuilder uriComponentsBuilder, final BigInteger id, final String path) {
        return uriComponentsBuilder
                .path(path)
                .buildAndExpand(id)
                .toUri();
    }
}
