package br.com.luiza.labs.messagedeliveryspring.app.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@NoArgsConstructor
@Getter
@Builder
public class MessageDTO {

    @NotNull(message = "Entre com uma data valida")
    private Calendar dateTimeSchedule;

    @NotBlank
    private String recipient;

    @NotBlank
    private String message;

    @NotNull(message = "Entre com um tipo valido")
    private String messageType;

    public MessageDTO(@NotNull(message = "Entre com uma data valida") Calendar dateTimeSchedule, @NotBlank String recipient, @NotBlank String message, @NotNull(message = "Entre com um tipo valido") String messageType) {
        this.dateTimeSchedule = dateTimeSchedule;
        this.recipient = recipient;
        this.message = message;
        this.messageType = messageType;
    }
}
