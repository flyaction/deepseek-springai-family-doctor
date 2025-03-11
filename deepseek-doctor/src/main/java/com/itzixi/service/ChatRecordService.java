package com.itzixi.service;

import com.itzixi.bean.ChatRecord;
import com.itzixi.utils.ChatTypeEnum;

import java.util.List;

/**
 * @author: action
 * @create: 2025/3/10 11:12
 **/
public interface ChatRecordService {
    /**
     * 保存用户和AI的聊天记录
     *
     * @param userName
     * @param message
     * @param chatType
     * @return
     */
    public void saveChatRecord(String userName, String message, ChatTypeEnum chatType);

    /**
     * 查询用户和AI的历史聊天记录
     * @param userName
     * @return List<ChatRecord>
     */
    public List<ChatRecord> getChatRecordList(String userName);
}
