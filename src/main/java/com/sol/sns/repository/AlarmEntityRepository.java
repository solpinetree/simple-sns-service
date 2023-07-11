package com.sol.sns.repository;

import com.sol.sns.model.entity.AlarmEntity;
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
public class AlarmEntityRepository {

    private final JdbcTemplate jdbcTemplate;

    public Page<AlarmEntity> findAllByUserId(Integer userId, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;

        List<AlarmEntity> alarmEntities;

        try {
            alarmEntities = jdbcTemplate.query(
                    "select * from \"alarm\" where user_id = ? and deleted_at is null limit ? offset ?",
                    new BeanPropertyRowMapper<>(AlarmEntity.class),
                    userId, pageSize, offset);
        } catch (EmptyResultDataAccessException e) {
            alarmEntities = List.of();
        }

        return new PageImpl<>(alarmEntities, pageable, alarmEntities.size());
    }

    public AlarmEntity save(AlarmEntity entity) {
        int alarmId = jdbcTemplate.update(
                "insert into \"alarm\" (user_id, alarmType, args, registered_at, updated_at) values (?, ?, ?, now(), now())",
                entity.getUser().getId(), entity.getAlarmType(), entity.getArgs());

        entity.setId(alarmId);
        return entity;
    }
}
