package com.sol.sns.repository;

import com.sol.sns.model.entity.LikeEntity;
import com.sol.sns.model.entity.PostEntity;
import com.sol.sns.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LikeEntityRepository {

    private final JdbcTemplate jdbcTemplate;

    public boolean findByUserAndPost(UserEntity user, PostEntity post){
        boolean result;

        try {
            result = jdbcTemplate.queryForObject(
                    "select 1 from \"like\" where user_id =  ? and post_id = ? and deleted_at is null",
                    Boolean.class,
                    user.getId(), post.getId());
        } catch (EmptyResultDataAccessException e) {
            result = false;
        }

        return result;
    }

    public int countByPost(PostEntity post) {
        int cnt = jdbcTemplate.queryForObject("select count(*) from \"like\" where post_id = ? and deleted_at is null",
                Integer.class,
                post.getId());

        return cnt;
    }

    public Optional<LikeEntity> save(LikeEntity entity) {
        try {
            int likeId = jdbcTemplate.update(
                    "insert into \"like\" (user_id, post_id, registered_at, updated_at) values (?, ?, now(), now())",
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
