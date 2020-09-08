package br.com.luiza.labs.messagedeliveryspring.app.validations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@AllArgsConstructor
//@Setter
//@Getter
public class ErrorResponse {
    private final String message;
    private final String objectName;
    private final List<ErrorObject> errors;

    public ErrorResponse(String message, String objectName, List<ErrorObject> errors) {
        this.message = message;
        this.objectName = objectName;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public String getObjectName() {
        return objectName;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }
}
