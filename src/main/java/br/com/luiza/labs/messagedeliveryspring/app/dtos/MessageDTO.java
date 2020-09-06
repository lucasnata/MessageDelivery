package br.com.luiza.labs.messagedeliveryspring.app.dtos;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {

    @NotNull
    private Calendar dateTimeSchedule;

    @NotNull
    private String recipient;

    @NotNull
    private String message;

    private MessageType messageType;
}
