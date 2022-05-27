package com.github.javarushcommunity.tba.command;

class StatisticCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.STATISTIC.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(StatisticCommand.STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatisticCommand(sendBotMessageService, telegramUserService);
    }
}
