package br.com.luiza.labs.messagedeliveryspring.app.mappers;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;

import java.util.Calendar;

public class MessageMapper {
    public static Message messageDTOToEntity(MessageDTO messageDTO, Recipient recipient) {
        Message message = new Message();
//        Recipient recipient = new Recipient();
//        recipient.setContact(messageDTO.getRecipient());
//        recipient.setType(messageDTO.getMessageType());
        message.setRecipient(recipient);
        message.setDateTimeSchedule(messageDTO.getDateTimeSchedule());
        message.setMessage(messageDTO.getMessage());
        message.setMessageType(messageDTO.getMessageType());
        message.setMessageStatus(MessageStatus.SCHEDULED);
        message.setCreatedAt(Calendar.getInstance());
        System.out.println(message);
        return message;
    }
}
