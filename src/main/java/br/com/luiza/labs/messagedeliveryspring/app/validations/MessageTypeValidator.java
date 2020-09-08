package br.com.luiza.labs.messagedeliveryspring.app.validations;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MessageTypeValidator implements ConstraintValidator<MessageTypeValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        for (MessageType mt: MessageType.values()) {
            if (mt.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
