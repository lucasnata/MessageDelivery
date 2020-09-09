package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.app.repositories.RecipientRepository;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecipientService implements IRecipientService{

    @Autowired
    RecipientRepository recipientRepository;

    public Optional<Recipient> addRecipient(String contact, MessageType type){
        return this.recipientRepository.findByContact(contact)
            .or(() -> this.saveRecipient(contact, type));
    }

    private Optional<Recipient> saveRecipient(String contact, MessageType type) {
        return Optional.of(this.recipientRepository.save(instanceOfRecipient(contact, type)));
    }

    private Recipient instanceOfRecipient(String contact, MessageType type) {
        return new Recipient(contact, type);
    }
}
