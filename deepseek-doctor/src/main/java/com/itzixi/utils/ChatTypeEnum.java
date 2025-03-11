package com.itzixi.utils;

/**
 * @author: action
 * @create: 2025/3/11 17:10
 **/
public enum ChatTypeEnum {

    USER("user", "用户发的内容"),
    BOT("bot", "AI回复的内容");

    public final String type;
    public final String value;

    ChatTypeEnum(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
