package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.repositories.MessageRepository;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class MessageServiceTest {

    MessageRepository messageRepository;
    RecipientService recipientService;
    MessageService messageService;
    RabbitMQSender rabbitMQSender;

    final String email = "meu@email.com";
    final BigInteger id = new BigInteger("2");

    @BeforeEach
    void setUp() {
        this.messageRepository = spy(MessageRepository.class);
        this.recipientService = mock(RecipientService.class);
        this.messageService = new MessageService(this.messageRepository, this.recipientService, this.rabbitMQSender);
    }

    @Test
    void shouldAddMessageAndReturnId() {
        when(this.recipientService.addRecipient(email, MessageType.EMAIL)).thenReturn(Optional.of(Recipient.builder().contact(email).build()));
        when(this.messageRepository.save(any(Message.class))).thenReturn(Message.builder().id(id).build());
        Message message = this.messageService.addMessage(MessageDTO.builder().recipient(email).messageType(MessageType.EMAIL.name()).build()).get();
        verify(this.messageRepository, Mockito.times(1)).save(any(Message.class));
        assertEquals(message.getId(), id);
    }

    @Test
    void shouldGetMessageAndReturnStatus() {
        when(this.messageRepository.findByMessageStatus(MessageStatus.SCHEDULED))
            .thenReturn(Arrays.asList(
                Message.builder().id(id)
                    .dateTimeSchedule(Calendar.getInstance())
                    .recipient(Recipient.builder().contact(email).type(MessageType.EMAIL).build())
                    .message("minha mensagem")
                    .messageStatus(MessageStatus.SCHEDULED)
                    .messageType(MessageType.EMAIL)
                    .build()));

        Optional<Message> optionalMessage = this.messageService.getMessage(this.id);

        if (optionalMessage.isPresent()) {
            final Message message = optionalMessage.get();

            verify(this.messageRepository, times(1)).findByMessageStatus(MessageStatus.SCHEDULED);
            assertNotNull(message);
            assertEquals(this.email, message.getRecipient());
            assertEquals(MessageType.EMAIL.name(), message.getMessageType().name());
            assertEquals(MessageStatus.SCHEDULED.name(), message.getMessageType().name());
        } else {
            assertTrue(false);
        }
    }

    @Test
    void shouldDeleteMessageAndReturnStatus() {
        final Message message =
                Message.builder()
                        .id(this.id)
                        .recipient(Recipient.builder().contact(email).build())
                        .messageType(MessageType.EMAIL)
                        .messageStatus(MessageStatus.SCHEDULED)
                        .build();

        when(this.messageRepository.findById(id)).thenReturn(Optional.of(message));
        when(this.messageRepository.save(message)).thenReturn(message);

        final Message messageDeleted = this.messageService.deleteMessage(this.id).get();
        final ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);

        verify(this.messageRepository, times(1)).findById(this.id);
        verify(this.messageRepository, times(1)).save(captor.capture());
        assertEquals(MessageStatus.DELETED, captor.getValue().getMessageStatus());
        assertNotNull(messageDeleted);
        assertEquals(this.email, messageDeleted.getRecipient().getContact());
        assertEquals(MessageType.EMAIL.name(), messageDeleted.getMessageType().name());
        assertEquals(MessageStatus.DELETED.name(), messageDeleted.getMessageStatus().name());
    }

    @Test
    void getMessages() {
        this.recipientService.addRecipient(email, MessageType.EMAIL);
        Message message = this.messageRepository.save(any(Message.class));
        when(this.messageRepository.findByMessageStatus(MessageStatus.SCHEDULED)).thenReturn(Arrays.asList(message));
    }
}