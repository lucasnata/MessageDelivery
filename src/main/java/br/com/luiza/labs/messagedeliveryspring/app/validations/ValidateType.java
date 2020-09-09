package br.com.luiza.labs.messagedeliveryspring.app.validations;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.Builder;

@Builder
public class ValidateType implements IValidate {
    private String value;

    @Override
    public boolean isValid() {
        for(MessageType m: MessageType.values()){
            if(m.name().equals(value)){
                return true;
            }
        }
        return false;
    }
}
