package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;

public interface IRabbitMQSender {
    void send(Message message);
}
