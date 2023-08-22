package com.sol.sns.repository;

import com.sol.sns.model.Alarm;
import com.sol.sns.model.AlarmArgs;
import com.sol.sns.model.AlarmType;
import com.sol.sns.model.entity.AlarmEntity;
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
public class AlarmEntityRepository {

    private final JdbcTemplate jdbcTemplate;

    public Page<AlarmEntity> findAllByUserId(Integer userId, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = pageNumber * pageSize;

        List<AlarmEntity> alarmEntities;

        try {
            alarmEntities = jdbcTemplate.query(
                    "select * from \"alarm\" where user_id = ? and deleted_at is null order by registered_at desc limit ? offset ?",
                    rs -> {
                        List<AlarmEntity> result = new ArrayList<>();
                        while (rs.next()) {
                            UserEntity user = new UserEntity();
                            user.setId(userId);

                            AlarmEntity alarm = new AlarmEntity();
                            alarm.setId(rs.getInt("id"));
                            alarm.setAlarmType(AlarmType.valueOf(rs.getString("alarm_type")));
                            alarm.setArgs(AlarmArgs.fromJson(rs.getString("args")));
                            alarm.setUser(user);
                            alarm.setRegisteredAt(rs.getTimestamp("registered_at"));
                            alarm.setUpdatedAt(rs.getTimestamp("updated_at"));
                            alarm.setDeletedAt(rs.getTimestamp("deleted_at"));
                            result.add(alarm);
                        }
                        return result;
                    },
                    userId, pageSize, offset);
        } catch (EmptyResultDataAccessException e) {
            alarmEntities = List.of();
        }

        return new PageImpl<>(alarmEntities, pageable, alarmEntities.size());
    }

    public AlarmEntity save(AlarmEntity entity) {

        String argsJson = "{\"fromUserId\": " + entity.getArgs().getFromUserId() +
                ", \"targetId\": " + entity.getArgs().getTargetId() + "}";

        int alarmId = jdbcTemplate.update(
                "insert into \"alarm\" (user_id, alarm_type, args, registered_at, updated_at) values (?, ?, cast(? as json), now(), now())",
                entity.getUser().getId(), entity.getAlarmType().name(), argsJson);

        entity.setId(alarmId);
        return entity;
    }
}
