package br.com.luiza.labs.messagedeliveryspring.app.mappers;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageStatusDTO;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.util.Calendar;
import java.util.Optional;

public class MessageMapper {
    public static Message messageDTOtoEntity(MessageDTO messageDTO, Recipient recipient) {
        return new Message(
                messageDTO.getDateTimeSchedule(),
                recipient,
                messageDTO.getMessage(),
                messageDTO.getMessageType(),
                MessageStatus.SCHEDULED,
                Calendar.getInstance()
        );
    }

    public static MessageDTO messageEntitytoDTO(Message message) {
        return new MessageDTO(
                message.getDateTimeSchedule(),
                message.getRecipient().getContact(),
                message.getMessage(),
                message.getMessageType()
        );
    }

    public static MessageDTO messageEntitytoDTO(Optional<Message> messageOptional) {
        return messageEntitytoDTO(messageOptional.get());
    }


    public static MessageStatusDTO messageEntitytoMessageStatusDTO(Message message) {
        return new MessageStatusDTO(message.getId(), message.getMessageStatus());
    }
}
