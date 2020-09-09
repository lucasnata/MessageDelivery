package br.com.luiza.labs.messagedeliveryspring.app.validations;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public class ErrorList implements Serializable {
    List<ErrorObject> errors;
}
