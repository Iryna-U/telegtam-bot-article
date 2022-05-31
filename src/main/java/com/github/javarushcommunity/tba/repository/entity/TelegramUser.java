package com.github.javarushcommunity.tba.repository.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "tg_user")
public class TelegramUser {
    @Id
    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "active")
    private boolean active;
}
