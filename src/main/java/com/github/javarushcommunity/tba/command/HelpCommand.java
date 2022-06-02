package com.github.javarushcommunity.tba.command;

import static com.github.javarushcommunity.tba.command.CommandName.HELP;
import static com.github.javarushcommunity.tba.command.CommandName.START;
import static com.github.javarushcommunity.tba.command.CommandName.STOP;

import com.github.javarushcommunity.tba.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {
    public static final String HELP_MESSAGE = String.format("✨<b>Дотупные команды</b>✨\n\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n"
                    + "%s - получить помощь в работе со мной\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
