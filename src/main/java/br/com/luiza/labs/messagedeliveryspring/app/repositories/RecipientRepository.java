package br.com.luiza.labs.messagedeliveryspring.app.repositories;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    Optional<Recipient> findByContact(String contact);
}
