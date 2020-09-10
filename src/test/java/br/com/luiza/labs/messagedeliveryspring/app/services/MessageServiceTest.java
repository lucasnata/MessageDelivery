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

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    MessageRepository messageRepository;
    IRecipientService recipientService;
    IMessageService messageService;
    IRabbitMQSender rabbitMQSender;

    final String email = "meu@email.com";
    final BigInteger id = new BigInteger("2");

    @BeforeEach
    void setUp() {
        this.messageRepository = spy(MessageRepository.class);
        this.recipientService = mock(RecipientService.class);
        this.rabbitMQSender = mock(RabbitMQSender.class);
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
        when(this.recipientService.addRecipient(email, MessageType.EMAIL)).thenReturn(Optional.of(Recipient.builder().contact(email).build()));
        when(this.messageRepository.save(any(Message.class))).thenReturn(Message.builder().id(id).build());
        Message message = this.messageService.addMessage(MessageDTO.builder().recipient(email).messageType(MessageType.EMAIL.name()).build()).get();
        verify(this.messageRepository, Mockito.times(1)).save(any(Message.class));
        assertEquals(message.getId(), id);
    }
}