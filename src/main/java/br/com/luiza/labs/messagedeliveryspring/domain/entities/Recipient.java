package br.com.luiza.labs.messagedeliveryspring.domain.entities;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "recipients")
@NoArgsConstructor
@Getter
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String contact;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MessageType type;

    public Recipient(@NotNull String contact, @NotNull MessageType type) {
        this.contact = contact;
        this.type = type;
    }
}
