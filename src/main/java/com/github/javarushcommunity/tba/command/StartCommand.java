package com.github.javarushcommunity.tba.command;

import com.github.javarushcommunity.tba.repository.entity.TelegramUser;
import com.github.javarushcommunity.tba.service.SendBotMessageService;
import com.github.javarushcommunity.tba.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {
    public static final String START_MESSAGE = "Привет. Я Telegram Bot. "
            + "Я помогу тебе быть в курсе последних статей тех авторов, котрые тебе интересны. "
            + "Я еще маленький и только учусь.";

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    // Здесь не добавляем сервис через получение из Application Context.
    // Потому что если это сделать так, то будет циклическая зависимость, которая
    // ломает работу приложения.
    public StartCommand(SendBotMessageService sendBotMessageService,
                        TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                }
        );

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                START_MESSAGE);
    }
}
