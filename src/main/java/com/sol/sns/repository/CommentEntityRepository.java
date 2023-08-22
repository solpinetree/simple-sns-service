package com.sol.sns.repository;

import com.sol.sns.model.entity.CommentEntity;
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

import java.util.ArrayList;
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
                    "select c.*, u.user_name from \"comment\" c " +
                            "left join \"user\" u on c.user_id = u.id "+
                            "where c.post_id = ? and c.deleted_at is null order by c.registered_at desc limit ? offset ?",
                    rs -> {
                        List<CommentEntity> result = new ArrayList<>();
                        while (rs.next()) {
                            UserEntity user = new UserEntity();
                            user.setUserName(rs.getString("user_name"));
                            user.setId(rs.getInt("user_id"));
                            CommentEntity comment = new CommentEntity();
                            comment.setId(rs.getInt("id"));
                            comment.setComment(rs.getString("comment"));
                            comment.setUser(user);
                            comment.setRegisteredAt(rs.getTimestamp("registered_at"));
                            comment.setUpdatedAt(rs.getTimestamp("updated_at"));
                            comment.setDeletedAt(rs.getTimestamp("deleted_at"));
                            result.add(comment);
                        }
                        return result;
                    },
                    post.getId(), pageSize, offset);
        } catch (EmptyResultDataAccessException e) {
            commentEntities = List.of();
        }

        return new PageImpl<>(commentEntities, pageable, commentEntities.size());
    }

    public CommentEntity save(CommentEntity entity) {
        int commentId = jdbcTemplate.update(
                "insert into \"comment\" (user_id, post_id, comment, registered_at, updated_at) values (?, ?, ?, now(), now())",
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
