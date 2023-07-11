package com.sol.sns.model.entity;

import com.sol.sns.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
public class UserEntity {
    private Integer id;

    private String userName;

    private String password;

    private UserRole role = UserRole.USER;

    private Timestamp registeredAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static UserEntity of(String userName, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassword(password);
        return userEntity;
    }
}
