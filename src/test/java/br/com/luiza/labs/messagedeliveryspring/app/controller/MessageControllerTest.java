package br.com.luiza.labs.messagedeliveryspring.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddMessage() throws Exception{
        String body = "{\n" +
                "    \"dateTimeSchedule\": \"2020-09-06T02:10:43.511Z\",\n" +
                "    \"recipient\": \"contato\",\n" +
                "    \"message\": \"TESTE MENSAGEM\",\n" +
                "    \"messageType\": \"EMAIL\"\n" +
                "}";
        this.mockMvc.perform(
            post("/message")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(body.getBytes())
        ).andExpect(status().isOk());
    }
}
