package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.app.repositories.RecipientRepository;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class RecipientServiceTest {

    RecipientService recipientService;
    RecipientRepository recipientRepository;
    final String email = "meu@email.com";

    @BeforeEach
    void setUp() {
        this.recipientRepository = spy(RecipientRepository.class);
        this.recipientService = new RecipientService(this.recipientRepository);
    }

    @Test
    void shouldAddRecipient() {
        final var contact = "emiail@me.com";
        when(this.recipientRepository.findByContact(contact)).thenReturn(Optional.of(Recipient.builder().build()));
        Optional<Recipient> recipient = this.recipientService.addRecipient(contact, MessageType.EMAIL);
        assertTrue(recipient.isPresent());
    }
}