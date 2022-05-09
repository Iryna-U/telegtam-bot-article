package com.github.javarushcommunity.tba.command;

import static com.github.javarushcommunity.tba.command.CommandName.NO;
import static com.github.javarushcommunity.tba.command.NoCommand.NO_MESSAGE;

public class NoCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}
