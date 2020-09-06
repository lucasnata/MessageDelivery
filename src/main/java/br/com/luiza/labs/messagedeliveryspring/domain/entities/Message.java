package br.com.luiza.labs.messagedeliveryspring.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    private Calendar dateTimeSchedule;
    private String recipient;
    private String message;
}
