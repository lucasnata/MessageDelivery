package br.com.luiza.labs.messagedeliveryspring.app.validations;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class ErrorObject implements Serializable {
    private final String message;
    private final String field;
}
