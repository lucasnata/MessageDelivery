package br.com.luiza.labs.messagedeliveryspring.app.validations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTypeTest {

    @Test
    void shouldMessageTypeIsValid() {
        ValidateType validateType = new ValidateType("SMS");
        assertTrue(validateType.isValid());
    }

    @Test
    void shouldMessageTypeIsInvalid() {
        ValidateType validateType = new ValidateType("EMAI");
        assertFalse(validateType.isValid());
    }
}