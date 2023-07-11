package com.sol.sns.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class LikeEntity {
    private Integer id;

    private UserEntity user;

    private PostEntity post;

    private Timestamp registeredAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public static LikeEntity of(UserEntity userEntity, PostEntity postEntity) {
        LikeEntity entity = new LikeEntity();
        entity.setUser(userEntity);
        entity.setPost(postEntity);
        return entity;
    }
}
