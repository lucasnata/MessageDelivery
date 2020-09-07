package br.com.luiza.labs.messagedeliveryspring.domain.entities;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "messages")
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
}
