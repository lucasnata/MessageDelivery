package br.com.luiza.labs.messagedeliveryspring.app.validations;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorObject {
    private String message;
    private String field;
}
