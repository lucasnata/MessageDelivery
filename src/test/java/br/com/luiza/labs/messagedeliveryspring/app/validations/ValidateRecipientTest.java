package br.com.luiza.labs.messagedeliveryspring.app.validations;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateRecipientTest {

    @Test
    void shouldEmailsIsValid() {
        ValidateRecipient validateRecipient = new ValidateRecipient("meu@email.com", MessageType.EMAIL);
        assertTrue(validateRecipient.isValid());
    }

    @Test
    void shouldEmailsIsInvalid() {
        ValidateRecipient validateRecipient = new ValidateRecipient("meuXXX", MessageType.EMAIL);
        assertFalse(validateRecipient.isValid());
    }

    @Test
    void shouldSMSIsInvalid() {
        ValidateRecipient validateRecipient = new ValidateRecipient("999", MessageType.SMS);
        assertFalse(validateRecipient.isValid());
    }

    @Test
    void shouldWhatsAppIsValid() {
        ValidateRecipient validateRecipient = new ValidateRecipient("88888888888", MessageType.WHATSAPP);
        assertTrue(validateRecipient.isValid());
    }
}