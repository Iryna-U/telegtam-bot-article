package com.github.javarushcommunity.tba.repository;

import com.github.javarushcommunity.tba.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = NONE)
@DisplayName("Integration-level testing for TelegramUserRepository")
public class TelegramUserRepositoryITest {

    @Autowired
    private TelegramUserRepository telegramUserRepository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    void findAllUsersByActiveTrue_Test() {
        List<TelegramUser> users = telegramUserRepository.findAllByActiveTrue();
        Assertions.assertEquals(5, users.size());
    }

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    void findTelegramUserById_Test() {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId("1111111111");
        telegramUser.setActive(true);
        telegramUserRepository.save(telegramUser);

        Optional<TelegramUser> user = telegramUserRepository.findById(telegramUser.getChatId());
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(telegramUser, user.get());
    }

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    void saveTelegramUser_Test() {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId("1234567890");
        telegramUser.setActive(true);
        telegramUserRepository.save(telegramUser);

        Optional<TelegramUser> saved = telegramUserRepository.findById(telegramUser.getChatId());
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(telegramUser, saved.get());
    }
}
