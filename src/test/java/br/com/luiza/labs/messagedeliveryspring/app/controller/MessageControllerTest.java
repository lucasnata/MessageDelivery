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

    @Test
    void shouldAddMessageAndGetResponseOKAndGetLocation() throws Exception {
        this.addMessage().andExpect(status().isCreated());
    }

    @Test
    void shouldGetMessageStatus() throws Exception {
        this.addMessage();
        this.mockMvc.perform(get("/message/2/status")).andExpect(status().isOk());
    }

    @Test
    void shouldDeleteMessage() throws Exception {
        this.addMessage();
        this.mockMvc.perform(delete("/message/2")).andExpect(status().isOk());
    }

    private ResultActions addMessage() throws Exception {

        String body = "{\n" +
                "    \"dateTimeSchedule\": \"2020-09-06T02:10:43.511Z\",\n" +
                "    \"recipient\": \"contato\",\n" +
                "    \"message\": \"TESTE MENSAGEM\",\n" +
                "    \"messageType\": \"EMAIL\"\n" +
                "}";

        return this.mockMvc.perform(
                post("/message")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(body.getBytes())
        );
    }

}
