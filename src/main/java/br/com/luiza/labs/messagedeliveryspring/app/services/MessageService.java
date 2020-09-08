package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.mappers.MessageMapper;
import br.com.luiza.labs.messagedeliveryspring.app.repositories.MessageRepository;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
@Transactional
@AllArgsConstructor
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    RecipientService recipientService;

    public Optional<Message> addMessage(final MessageDTO messageDTO) {
        return recipientService
            .addRecipient(messageDTO.getRecipient(), messageDTO.getMessageType())
            .map(recipient -> MessageMapper.messageDTOtoEntity(messageDTO, recipient))
            .map(this.messageRepository::save);
    }

    public List<Message> getMessages() {
        return messageRepository.findByMessageStatus(MessageStatus.SCHEDULED);
    }

    public Optional<Message> getMessage(BigInteger id) {
        return messageRepository.findById(id);
    }

    public Optional<Message> deleteMessage(BigInteger id) {
        return messageRepository.findById(id)
            .map(message -> {message.setMessageStatus(MessageStatus.DELETED); return message;})
            .map(messageRepository::save);
    }
}
