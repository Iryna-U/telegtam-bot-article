package com.github.javarushcommunity.tba.bot;

import static com.github.javarushcommunity.tba.command.CommandName.NO;

import com.github.javarushcommunity.tba.command.CommandContainer;
import com.github.javarushcommunity.tba.service.SendBotMessageServiceImpl;
import com.github.javarushcommunity.tba.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBotArticles extends TelegramLongPollingBot {
    private static String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;

    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;

    public TelegramBotArticles(TelegramUserService telegramUserService) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this),
                telegramUserService);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    //   це і є точка входу, куди надходитимуть повідомлення від користувачів.
    //   Звідси йтиме вся нова логіка
    //   після отримання оновлень
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }

    }
}
