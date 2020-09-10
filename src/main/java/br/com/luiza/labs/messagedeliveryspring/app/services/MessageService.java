package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.mappers.MessageMapper;
import br.com.luiza.labs.messagedeliveryspring.app.repositories.MessageRepository;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService implements IMessageService{

    private final MessageRepository messageRepository;
    private final IRecipientService recipientService;
    private final IRabbitMQSender rabbitMQSender;

    public Optional<Message> addMessage(final MessageDTO messageDTO) {
        Optional<Message> optMessage = recipientService
                .addRecipient(messageDTO.getRecipient(), MessageType.valueOf(messageDTO.getMessageType()))
                .map(recipient -> MessageMapper.messageDTOtoEntity(messageDTO, recipient))
                .map(this.messageRepository::save);
        optMessage.ifPresent(rabbitMQSender::send);
        return optMessage;
    }

    public List<Message> getMessages() {
        return messageRepository.findByMessageStatus(MessageStatus.SCHEDULED);
    }

    public Optional<Message> getMessage(final BigInteger id) {
        return messageRepository.findById(id);
    }

    public Optional<Message> deleteMessage(final BigInteger id) {
        Optional<Message> optMessage = messageRepository.findById(id)
                .map(message -> {
                    message.setMessageStatus(MessageStatus.DELETED);
                    return message;
                })
                .map(messageRepository::save);
        optMessage.ifPresent(rabbitMQSender::send);
        return optMessage;
    }
}
