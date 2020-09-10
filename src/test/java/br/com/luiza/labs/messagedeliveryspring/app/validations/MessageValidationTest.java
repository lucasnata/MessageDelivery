package br.com.luiza.labs.messagedeliveryspring.app.validations;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageValidationTest {

    @Test
    void shouldValidateMessageTypeWithErrors() {
        MessageValidation messageValidation = new MessageValidation(MessageDTO.builder().messageType("SM").build());
        assertFalse(messageValidation.haveNoErrors());
    }

    @Test
    void shouldValidateMessageTypeWithoutErrors() {
        MessageValidation messageValidation = new MessageValidation(MessageDTO.builder().messageType("PUSH").build());
        assertTrue(messageValidation.haveNoErrors());
    }

    @Test
    void shouldValidateRecipientWithErros() {
        MessageValidation messageValidation = new MessageValidation(MessageDTO.builder()
                .messageType("EMAIL").recipient("ASm").build());
        assertFalse(messageValidation.haveNoErrors());
    }

    @Test
    void shouldValidateRecipientWithoutErros() {
        MessageValidation messageValidation = new MessageValidation(MessageDTO.builder()
                .messageType("EMAIL").recipient("email@meu.com").build());
        assertTrue(messageValidation.haveNoErrors());
    }
}