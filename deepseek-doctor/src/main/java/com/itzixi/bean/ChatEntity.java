package com.itzixi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: action
 * @create: 2025/3/11 11:14
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity {
    private String currentUserName;
    private String message;
}
