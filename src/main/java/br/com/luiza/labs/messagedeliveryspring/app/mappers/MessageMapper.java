package br.com.luiza.labs.messagedeliveryspring.app.mappers;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;

public class MessageMapper {
    public static Message messageDTOToEntity(MessageDTO messageDTO) {
        Message message = new Message();
        message.setDateTimeSchedule(messageDTO.getDateTimeSchedule());
        message.setRecipient(messageDTO.getRecipient());
        message.setMessage(messageDTO.getMessage());
        message.setMessageType(messageDTO.getMessageType());
        return message;
    }
}
