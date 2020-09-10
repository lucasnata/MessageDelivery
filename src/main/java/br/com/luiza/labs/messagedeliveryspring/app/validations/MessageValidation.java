package br.com.luiza.labs.messagedeliveryspring.app.validations;

import br.com.luiza.labs.messagedeliveryspring.app.dtos.MessageDTO;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
public class MessageValidation {

    @Getter
    private final List<ErrorObject> errors = new ArrayList<>();
    private final MessageDTO messageDTO;

    public MessageValidation(final MessageDTO messageDTO) {
        this.messageDTO = messageDTO;
        this.validateMessageType();
        if (haveNoErrors()) this.validateRecipient();
    }

    private List<ErrorObject> validateMessageType() {
        boolean messageTypeNameIsValid = ValidateType.builder().value(messageDTO.getMessageType()).build().isValid();

        if (!messageTypeNameIsValid)
            errors.add(ErrorObject.builder().field("messageType").message("Tipo de mensagem inválido").build());

        return errors;
    }

    private List<ErrorObject> validateRecipient() {
        boolean recipientIsValid = ValidateRecipient.builder().recipient(messageDTO.getRecipient())
                    .type(MessageType.valueOf(messageDTO.getMessageType())).build().isValid();

        if (!recipientIsValid)
            errors.add(ErrorObject.builder().field("recipient").message("Destinatário inválido").build());

        return errors;
    }

    public boolean haveNoErrors(){
        return (errors.size() == 0);
    }
}
