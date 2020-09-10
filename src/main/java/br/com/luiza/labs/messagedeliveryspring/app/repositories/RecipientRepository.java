package br.com.luiza.labs.messagedeliveryspring.app.repositories;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, BigInteger> {
    Optional<Recipient> findByContact(String contact);
}
