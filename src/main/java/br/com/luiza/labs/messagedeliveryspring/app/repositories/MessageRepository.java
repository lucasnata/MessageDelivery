package br.com.luiza.labs.messagedeliveryspring.app.repositories;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, BigInteger> {
    List<Message> findByMessageStatus(MessageStatus status);
}
