package br.com.luiza.labs.messagedeliveryspring.app.dtos;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@AllArgsConstructor
@Getter
public class MessageStatusDTO {
    private BigInteger id;
    private MessageStatus status;
}
