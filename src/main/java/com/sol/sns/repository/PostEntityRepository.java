package com.sol.sns.repository;

import com.sol.sns.model.Post;
import com.sol.sns.model.entity.PostEntity;
import com.sol.sns.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostEntityRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<PostEntity> findById(int postId){
        PostEntity entity;

        try {
            entity = jdbcTemplate.queryForObject(
                    "select * from \"post\" where id = ? and deleted_at is null",
                    (ResultSet rs, int rowNum) -> {
                        PostEntity post = new PostEntity();
                        post.setTitle(rs.getString("title"));
                        post.setBody(rs.getString("body"));
                        post.setId(rs.getInt("id"));

                        UserEntity user = new UserEntity();
                        user.setId(rs.getInt("user_id"));
                        post.setUser(user);

                        return post;
                    },
                    postId);
        } catch (EmptyResultDataAccessException e) {
            entity = null;
        }

        return Optional.ofNullable(entity);
    }

    public Page<PostEntity> findAll(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;

        List<PostEntity> postEntities;

        try {
            postEntities = jdbcTemplate.query(
                    "select p.*, u.user_name from \"post\" p " +
                            "left join \"user\" u on p.user_id = u.id " +
                            "where p.deleted_at is null limit ? offset ?",
                    rs -> {
                        List<PostEntity> result = new ArrayList<>();
                        while (rs.next()) {
                            UserEntity user = new UserEntity();
                            user.setUserName(rs.getString("user_name"));
                            user.setId(rs.getInt("user_id"));
                            PostEntity post = new PostEntity();
                            post.setId(rs.getInt("id"));
                            post.setTitle(rs.getString("title"));
                            post.setBody(rs.getString("body"));
                            post.setUser(user);
                            post.setRegisteredAt(rs.getTimestamp("registered_at"));
                            post.setUpdatedAt(rs.getTimestamp("updated_at"));
                            post.setDeletedAt(rs.getTimestamp("deleted_at"));
                            result.add(post);
                        }
                        return result;
                    },
                    pageSize, offset);
        } catch (EmptyResultDataAccessException e) {
            postEntities = List.of();
        }

        return new PageImpl<>(postEntities, pageable, postEntities.size());
    }

    public Page<PostEntity> findAllByUser(UserEntity user, Pageable pageable){
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;

        List<PostEntity> postEntities;

        try {
            postEntities = jdbcTemplate.query(
                    "select * from \"post\" where user_id = ? and deleted_at is null order by registered_at desc limit ? offset ?",
                    rs -> {
                        List<PostEntity> result = new ArrayList<>();
                        while (rs.next()) {
                            PostEntity post = new PostEntity();
                            post.setId(rs.getInt("id"));
                            post.setTitle(rs.getString("title"));
                            post.setBody(rs.getString("body"));
                            post.setUser(user);
                            post.setRegisteredAt(rs.getTimestamp("registered_at"));
                            post.setUpdatedAt(rs.getTimestamp("updated_at"));
                            post.setDeletedAt(rs.getTimestamp("deleted_at"));
                            result.add(post);
                        }
                        return result;
                    },
                    user.getId(), pageSize, offset);
        } catch (EmptyResultDataAccessException e) {
            postEntities = List.of();
        }

        return new PageImpl<>(postEntities, pageable, postEntities.size());
    }

    public PostEntity save(PostEntity entity) {
        int postId = jdbcTemplate.update(
                "insert into \"post\" (title, body, user_id, registered_at, updated_at) values (?, ?, ?, now(), now())",
                entity.getTitle(), entity.getBody(), entity.getUser().getId());

        entity.setId(postId);
        return entity;
    }

    public PostEntity update(PostEntity entity) {
        jdbcTemplate.update(
                "update \"post\" set title = ?, body = ?, updated_at = now() where id = ?",
                entity.getTitle(), entity.getBody(), entity.getId());

        return entity;
    }

    public void delete(PostEntity entity) {
        jdbcTemplate.update(
                "update \"post\" set deleted_at = now() where post_id = ?"
                , entity.getId()
        );
    }
}
