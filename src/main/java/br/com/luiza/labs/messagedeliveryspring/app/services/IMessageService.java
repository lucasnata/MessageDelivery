package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface IMessageService {
    Optional<Message> addMessage(final MessageDTO messageDTO);
    List<Message> getMessages();
    Optional<Message> getMessage(final BigInteger id);
    Optional<Message> deleteMessage(final BigInteger id);
}
