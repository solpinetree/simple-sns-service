package com.sol.sns.repository;

import com.sol.sns.model.entity.CommentEntity;
import com.sol.sns.model.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentEntityRepository {

    private final JdbcTemplate jdbcTemplate;

    public Page<CommentEntity> findAllByPost(PostEntity post, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;

        List<CommentEntity> commentEntities;

        try {
            commentEntities = jdbcTemplate.query(
                    "select * from \"comment\" where post_id = ? and deleted_at is null limit ? offset ?",
                    new BeanPropertyRowMapper<>(CommentEntity.class),
                    post.getId(), pageSize, offset);
        } catch (EmptyResultDataAccessException e) {
            commentEntities = List.of();
        }

        return new PageImpl<>(commentEntities, pageable, commentEntities.size());
    }

    public CommentEntity save(CommentEntity entity) {
        int commentId = jdbcTemplate.update(
                "insert into \"comment\" (user_id, post_id, comment) values (?, ?, ?)",
                entity.getUser().getId(), entity.getPost().getId(), entity.getComment());

        entity.setId(commentId);
        return entity;
    }

    public void deleteAllByPost(PostEntity post) {
        jdbcTemplate.update(
                "update \"comment\" set deleted_at = now() where post_id = ?",
                post.getId());
    }
}
