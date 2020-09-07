package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.mappers.MessageMapper;
import br.com.luiza.labs.messagedeliveryspring.app.repositories.MessageRepository;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    RecipientService recipientService;

    public Optional<UUID> addMessage(final MessageDTO messageDTO) {
        return recipientService
            .addRecipient(messageDTO.getRecipient(), messageDTO.getMessageType())
            .map(recipient -> MessageMapper.messageDTOToEntity(messageDTO, recipient))
            .map(this.messageRepository::save)
            .map(Message::getId);
    }
}
