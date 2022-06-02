package com.github.javarushcommunity.tba.repository;

import com.github.javarushcommunity.tba.repository.entity.TelegramUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} for handling with {@link TelegramUser} entity.
 */
@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, String> {

    List<TelegramUser> findAllByActiveTrue();
}
