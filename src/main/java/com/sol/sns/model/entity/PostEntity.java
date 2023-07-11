package com.sol.sns.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
public class PostEntity {
    private Integer id;

    private String title;

    private String body;

    private UserEntity user;

    private Timestamp registeredAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public static PostEntity of(String title, String body, UserEntity userEntity) {
        PostEntity entity = new PostEntity();
        entity.setTitle(title);
        entity.setBody(body);
        entity.setUser(userEntity);
        return entity;
    }
}
