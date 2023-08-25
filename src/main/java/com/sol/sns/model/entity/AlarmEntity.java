package com.sol.sns.model.entity;

import com.sol.sns.model.AlarmArgs;
import com.sol.sns.model.AlarmType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AlarmEntity {
    private Integer id;

    // 알람을 받은 사람
    private Integer userId;

    private AlarmType alarmType;

    private AlarmArgs args;

    private Timestamp registeredAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public static AlarmEntity of(Integer userId, AlarmType alarmType, AlarmArgs args) {
        AlarmEntity entity = new AlarmEntity();
        entity.setUserId(userId);
        entity.setAlarmType(alarmType);
        entity.setArgs(args);
        return entity;
    }
}
