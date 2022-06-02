package com.github.javarushcommunity.tba.command;

import static com.github.javarushcommunity.tba.command.CommandName.HELP;
import static com.github.javarushcommunity.tba.command.CommandName.NO;
import static com.github.javarushcommunity.tba.command.CommandName.START;
import static com.github.javarushcommunity.tba.command.CommandName.STATISTIC;
import static com.github.javarushcommunity.tba.command.CommandName.STOP;

import com.github.javarushcommunity.tba.service.SendBotMessageService;
import com.github.javarushcommunity.tba.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService,
                        telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService,
                        telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(STATISTIC.getCommandName(), new StatisticCommand(sendBotMessageService,
                        telegramUserService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
