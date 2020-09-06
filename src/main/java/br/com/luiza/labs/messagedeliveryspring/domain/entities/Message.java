package br.com.luiza.labs.messagedeliveryspring.domain.entities;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateTimeSchedule;

    @NotNull
    private String recipient;

    @NotNull
    private String message;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;
}
