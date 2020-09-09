package br.com.luiza.labs.messagedeliveryspring.app.validations;

import br.com.luiza.labs.messagedeliveryspring.domain.vos.MessageType;
import lombok.Builder;

import java.util.regex.Pattern;

@Builder
public class ValidateRecipient implements IValidate {
    private String recipient;
    private MessageType type;

    @Override
    public boolean isValid() {
        switch (this.type){
            case EMAIL:
                return emailIsValid(this.recipient);
            case SMS:
            case WHATSAPP:
                return mobileNumberIsValid(this.recipient);
            case PUSH:
                return pushIsValid(this.recipient);
            default:
                return false;
        }
    }

    private boolean pushIsValid(String recipient) {
        return true;
    }

    private boolean mobileNumberIsValid(String recipient) {
        return Pattern.compile("\\d+").matcher(recipient).matches() && (recipient.length() > 10);
    }

    private boolean emailIsValid(String recipient) {
        return Pattern.compile("^(.+)@(.+)$").matcher(recipient).matches();
    }
}
