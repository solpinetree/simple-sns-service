package com.sol.sns.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmArgs {

    // 알람을 발생시킨 사람
    private Integer fromUserId;
    private String fromUserName;
    private Integer targetId;
    private String text;

    public static AlarmArgs fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, AlarmArgs.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AlarmArgs(Integer fromUserId, String fromUserName, Integer targetId) {
        this.fromUserId = fromUserId;
        this.fromUserName = fromUserName;
        this.targetId = targetId;
    }
}
