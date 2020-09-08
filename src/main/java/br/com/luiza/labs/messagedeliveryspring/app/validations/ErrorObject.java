package br.com.luiza.labs.messagedeliveryspring.app.validations;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
//@Getter
public class ErrorObject {
    private String message;
    private String field;
    private Object parameter;

    public ErrorObject() {
    }

    public ErrorObject(String message, String field, Object parameter) {
        this.message = message;
        this.field = field;
        this.parameter = parameter;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
