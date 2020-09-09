package br.com.luiza.labs.messagedeliveryspring.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String body = "{\n" +
            "    \"dateTimeSchedule\": \"2020-09-06T02:10:43.511Z\",\n" +
            "    \"recipient\": \"email@email.com\",\n" +
            "    \"message\": \"TESTE MENSAGEM\",\n" +
            "    \"messageType\": \"EMAIL\"\n" +
            "}";

    @Test
    void shouldAddMessageAndGetResponseOKAndGetLocation() throws Exception {
        this.addMessage(body).andExpect(status().isCreated());
    }

    @Test
    void shoulAddMessageWithWrongRecipient() throws Exception {
        final String bodyRecipientWrong = "{\n" +
                "    \"dateTimeSchedule\": \"2020-09-06T02:10:43.511Z\",\n" +
                "    \"recipient\": \"contato\",\n" +
                "    \"message\": \"TESTE MENSAGEM\",\n" +
                "    \"messageType\": \"EMAIL\"\n" +
                "}";
        this.addMessage(bodyRecipientWrong).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldAddMessage() throws Exception {
        final String bodyMessageTypeWrong = "{\n" +
                "    \"dateTimeSchedule\": \"2020-09-06T02:10:43.511Z\",\n" +
                "    \"recipient\": \"contato\",\n" +
                "    \"message\": \"TESTE MENSAGEM\",\n" +
                "    \"messageType\": \"ZAPZAP\"\n" +
                "}";
        this.addMessage(bodyMessageTypeWrong).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldGetMessageStatus() throws Exception {
        this.addMessage(body);
        this.mockMvc.perform(get("/message/2/status")).andExpect(status().isOk());
    }

    @Test
    void shouldGetMessageStatusWithWrongParam() throws Exception {
        this.addMessage(body);
        this.mockMvc.perform(get("/message/a/status")).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldDeleteMessage() throws Exception {
        this.addMessage(body);
        this.mockMvc.perform(delete("/message/2")).andExpect(status().isOk());
    }

    private ResultActions addMessage(String body) throws Exception {
        return this.mockMvc.perform(
                post("/message")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(body.getBytes())
        );
    }

}
