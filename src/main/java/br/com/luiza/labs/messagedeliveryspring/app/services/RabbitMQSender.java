package br.com.luiza.labs.messagedeliveryspring.app.services;

import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class RabbitMQSender implements IRabbitMQSender{

    private final AmqpTemplate rabbitTemplate;
    private final String exchange;
    private final String routingkey;

    @Autowired
    RabbitMQSender(final AmqpTemplate rabbitTemplate,
                   @Value("${message.rabbitmq.routingkey}") final String routingkey,
                   @Value("${message.rabbitmq.exchange}") final String exchange){
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingkey = routingkey;
    }

    public void send(final Message message) {
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
    }
}