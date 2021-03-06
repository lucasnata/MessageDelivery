package br.com.luiza.labs.messagedeliveryspring.app.mappers;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageStatusDTO;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Message;
import br.com.luiza.labs.messagedeliveryspring.domain.entities.Recipient;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;

import java.util.Calendar;

public class MessageMapper {
    public static Message messageDTOtoEntity(final MessageDTO messageDTO, final Recipient recipient) {
        return Message.builder()
                .dateTimeSchedule(messageDTO.getDateTimeSchedule())
                .recipient(recipient)
                .message(messageDTO.getMessage())
                .messageType(MessageType.valueOf(messageDTO.getMessageType()))
                .messageStatus(MessageStatus.SCHEDULED)
                .createdAt(Calendar.getInstance())
                .build();
    }

    public static MessageDTO messageEntitytoDTO(final Message message) {
        return new MessageDTO(
                message.getDateTimeSchedule(),
                message.getRecipient().getContact(),
                message.getMessage(),
                message.getMessageType().toString()
        );
    }

    public static MessageStatusDTO messageEntitytoMessageStatusDTO(final Message message) {
        return MessageStatusDTO.builder().id(message.getId()).status(message.getMessageStatus()).build();
    }
}
