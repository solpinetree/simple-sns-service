package com.sol.sns.repository;

import com.sol.sns.model.entity.LikeEntity;
import com.sol.sns.model.entity.PostEntity;
import com.sol.sns.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LikeEntityRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<LikeEntity> findByUserAndPost(UserEntity user, PostEntity post){
        LikeEntity entity;

        try {
            entity = jdbcTemplate.queryForObject(
                    "select * from \"like\" where user_id =  ? and post_id = ? and deleted_at = null",
                    LikeEntity.class,
                    user.getId(), post.getId());
        } catch (EmptyResultDataAccessException e) {
            entity = null;
        }

        return Optional.ofNullable(entity);
    }

    public int countByPost(@Param("post") PostEntity post) {
        int cnt = jdbcTemplate.queryForObject("select count(*) from \"post\" where id = ? and deleted_at = null",
                Integer.class,
                post.getId());
        return cnt;
    }

    public Optional<LikeEntity> save(LikeEntity entity) {
        try {
            int likeId = jdbcTemplate.update(
                    "insert into \"like\" (user_id, post_id) values (?, ?)",
                    entity.getUser().getId(), entity.getPost().getId());
            entity.setId(likeId);
        } catch (EmptyResultDataAccessException e) {
            entity = null;
        }

        return Optional.ofNullable(entity);
    }

    public void deleteAllByPost(PostEntity post) {
        jdbcTemplate.update(
                "update \"like\" set deleted_at = now() where post_id = ?"
                , post.getId()
        );
    }
}
