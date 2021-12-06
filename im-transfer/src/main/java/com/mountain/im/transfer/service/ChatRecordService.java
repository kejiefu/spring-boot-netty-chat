package com.mountain.im.transfer.service;

import com.mountain.common.domain.ChatRecord;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/7 10:03
 * @Created by kejiefu
 */
public interface ChatRecordService {

    /**
     * 发送聊天信息
     */
    void sendChatRecord(ChatRecord chatRecordDto);

}
