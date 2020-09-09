package br.com.luiza.labs.messagedeliveryspring.app.repositories;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface RecipientRepository extends JpaRepository<Recipient, BigInteger> {
    Optional<Recipient> findByContact(String contact);
}
