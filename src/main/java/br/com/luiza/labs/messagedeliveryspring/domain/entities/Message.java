package br.com.luiza.labs.messagedeliveryspring.domain.entities;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Calendar;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar dateTimeSchedule;

    @NotNull
    @OneToOne
    private Recipient recipient;

    @NotNull
    @Column(nullable = false)
    private String message;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Calendar createdAt;

    @Column(name = "modified_at")
    private Calendar modifiedAt;

    public Message(@NotNull Calendar dateTimeSchedule, @NotNull Recipient recipient, @NotNull String message, @NotNull MessageType messageType, @NotNull MessageStatus messageStatus, Calendar createdAt) {
        this.dateTimeSchedule = dateTimeSchedule;
        this.recipient = recipient;
        this.message = message;
        this.messageType = messageType;
        this.messageStatus = messageStatus;
        this.createdAt = createdAt;
    }
}
