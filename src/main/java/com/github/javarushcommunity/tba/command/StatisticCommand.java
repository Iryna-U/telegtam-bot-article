package com.github.javarushcommunity.tba.command;

import com.github.javarushcommunity.tba.service.SendBotMessageService;
import com.github.javarushcommunity.tba.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatisticCommand implements Command {
    public static final String STAT_MESSAGE = "Telegram Bot использует %s человек.";
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public StatisticCommand(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int count = telegramUserService.retrieveAllActiveUsers().size();
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                String.format(STAT_MESSAGE, count));
    }
}
