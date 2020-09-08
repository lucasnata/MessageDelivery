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
        return Message.builder()
                .dateTimeSchedule(messageDTO.getDateTimeSchedule())
                .recipient(recipient)
                .message(messageDTO.getMessage())
                .messageType(messageDTO.getMessageType())
                .messageStatus(MessageStatus.SCHEDULED)
                .createdAt(Calendar.getInstance())
                .build();
    }

    public static MessageDTO messageEntitytoDTO(Message message) {
        return new MessageDTO(
                message.getDateTimeSchedule(),
                message.getRecipient().getContact(),
                message.getMessage(),
                message.getMessageType()
        );
    }

    public static MessageStatusDTO messageEntitytoMessageStatusDTO(Message message) {
        return new MessageStatusDTO(message.getId(), message.getMessageStatus());
    }
}
