package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.app.repositories.RecipientRepository;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

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
        when(this.recipientRepository.findByContact(email))
                .thenReturn(Optional.of(Recipient.builder().contact(email).type(MessageType.EMAIL).build()));
    }
}