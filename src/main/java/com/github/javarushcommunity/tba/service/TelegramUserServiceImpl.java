package com.github.javarushcommunity.tba.service;

import com.github.javarushcommunity.tba.repository.TelegramUserRepository;
import com.github.javarushcommunity.tba.repository.entity.TelegramUser;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link TelegramUserService} interface.
 */
@Service
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return telegramUserRepository.findById(chatId);
    }
}
