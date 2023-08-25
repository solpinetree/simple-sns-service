package com.sol.sns.controller.response;

import com.sol.sns.model.Alarm;
import com.sol.sns.model.AlarmArgs;
import com.sol.sns.model.AlarmType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class AlarmResponse {
    private Integer id;
    private AlarmType alarmType;
    private AlarmArgs alarmArgs;
    private String text;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;


    public static AlarmResponse fromAlarm(Alarm alarm) {
        String text = "";
        String fromUserName = alarm.getArgs().getFromUserName();

        if (alarm.getAlarmType().equals(AlarmType.NEW_LIKE_ON_POST)) {
            text = fromUserName + "님이 회원님의 게시물을 좋아합니다 👍";
        } else {
            text = fromUserName + "님이 회원님의 게시물에 댓글을 달았습니다 ✋\n" +
                    alarm.getArgs().getText();
        }

        return new AlarmResponse(
                alarm.getId(),
                alarm.getAlarmType(),
                alarm.getArgs(),
                text,
                alarm.getRegisteredAt(),
                alarm.getUpdatedAt(),
                alarm.getDeletedAt()
        );
    }
}
