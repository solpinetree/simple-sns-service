package com.sol.sns.repository;

import com.sol.sns.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserEntityRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<UserEntity> findById(int userId){
        UserEntity entity;

        try {
            entity = jdbcTemplate.queryForObject(
                    "select * from \"user\" where id = ?",
                    (ResultSet rs, int rowNum) -> {
                        UserEntity user = UserEntity.of(rs.getString("user_name"), rs.getString("password"));
                        user.setId(rs.getInt("id"));
                        return user;
                    },
                    userId);
        } catch (EmptyResultDataAccessException e) {
            entity = null;
        }

        return Optional.ofNullable(entity);
    }

    public Optional<UserEntity> findByUserName(String userName){
        UserEntity entity;

        try {
            entity = jdbcTemplate.queryForObject(
                    "select * from \"user\" where user_name = ?",
                    (ResultSet rs, int rowNum) -> {
                        UserEntity user = UserEntity.of(rs.getString("user_name"), rs.getString("password"));
                        user.setId(rs.getInt("id"));
                        return user;
                    },
                    userName);
        } catch (EmptyResultDataAccessException e) {
            entity = null;
        }

        return Optional.ofNullable(entity);
    }

    public UserEntity save(UserEntity entity) {
        int userId = jdbcTemplate.update(
                "insert into \"user\" (user_name, password, role, registered_at, updated_at) values (?, ?, ?, now(), now())",
                entity.getUserName(), entity.getPassword(), entity.getRole().name());

        entity.setId(userId);
        return entity;
    }
}
