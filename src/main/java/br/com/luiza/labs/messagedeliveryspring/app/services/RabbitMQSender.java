package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender implements IRabbitMQSender{

    @Autowired
    AmqpTemplate rabbitTemplate;

    @Value("${message.rabbitmq.exchange}")
    private String exchange;

    @Value("${message.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Message message) {
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
    }
}
