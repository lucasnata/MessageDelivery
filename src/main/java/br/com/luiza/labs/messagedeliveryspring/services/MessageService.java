package br.com.luiza.labs.messagedeliveryspring.services;

import br.com.luiza.labs.messagedeliveryspring.app.repositories.MessageRepository;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public void addMessage(Message message) {
        messageRepository.save(message);
    }
}
