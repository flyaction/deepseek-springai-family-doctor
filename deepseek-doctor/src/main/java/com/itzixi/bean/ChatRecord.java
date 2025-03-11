package com.itzixi.bean;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 数据库表所对应的持久层对象
 * @author: action
 * @create: 2025/3/11 16:31
 **/
@Data
@ToString
public class ChatRecord {

    private String id;
    private String content;
    private String chatType;
    private LocalDateTime chatTime;
    private String familyMember;

}
