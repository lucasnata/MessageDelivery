package br.com.luiza.labs.messagedeliveryspring.app.validations;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.Builder;

import java.util.Arrays;

@Builder
public class ValidateType implements IValidate {
    private String value;

    @Override
    public boolean isValid() {
        return Arrays.stream(MessageType.values())
                .filter(t -> t.name().equals(value))
                .findFirst().isPresent();
    }
}
