package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;

import java.util.Optional;

public interface IRecipientService {
    Optional<Recipient> addRecipient(final String contact, final MessageType type);
}
