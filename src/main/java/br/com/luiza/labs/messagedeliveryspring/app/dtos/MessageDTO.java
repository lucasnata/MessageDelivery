package br.com.luiza.labs.messagedeliveryspring.app.dtos;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@NoArgsConstructor
@Getter
public class MessageDTO {

    @NotNull(message = "Entre com uma data valida")
    private Calendar dateTimeSchedule;

    @NotBlank
    private String recipient;

    @NotBlank
    private String message;

    @NotNull(message = "Entre com um tipo valido")
    private MessageType messageType;

    public MessageDTO(@NotNull(message = "Entre com uma data valida") Calendar dateTimeSchedule, @NotBlank String recipient, @NotBlank String message, @NotNull(message = "Entre com um tipo valido") MessageType messageType) {
        this.dateTimeSchedule = dateTimeSchedule;
        this.recipient = recipient;
        this.message = message;
        this.messageType = messageType;
    }
}
