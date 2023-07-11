package com.sol.sns.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
public class CommentEntity {
    private Integer id;

    private UserEntity user;

    private PostEntity post;

    private String comment;

    private Timestamp registeredAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public static CommentEntity of(UserEntity userEntity, PostEntity postEntity, String comment) {
        CommentEntity entity = new CommentEntity();
        entity.setUser(userEntity);
        entity.setPost(postEntity);
        entity.setComment(comment);
        return entity;
    }
}
