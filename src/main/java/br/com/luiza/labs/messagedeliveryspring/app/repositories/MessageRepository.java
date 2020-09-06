package br.com.luiza.labs.messagedeliveryspring.app.repositories;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
}
