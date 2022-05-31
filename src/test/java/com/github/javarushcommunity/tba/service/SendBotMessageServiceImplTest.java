package com.github.javarushcommunity.tba.service;

import com.github.javarushcommunity.tba.bot.TelegramBotArticles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//@SpringBootTest
//@ActiveProfiles("test") - для інтеграційних тестів
@DisplayName("Unit-level testing for SendBotMessageService")
class SendBotMessageServiceImplTest {
    private TelegramBotArticles bot;
    private SendBotMessageService sendBotMessageService;

    @BeforeEach
    public void init() {
        bot = Mockito.mock(TelegramBotArticles.class);
        sendBotMessageService = new SendBotMessageServiceImpl(bot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        //given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        //when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(bot).execute(sendMessage);
    }

}