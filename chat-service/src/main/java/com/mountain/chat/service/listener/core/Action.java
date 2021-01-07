package com.mountain.chat.service.listener.core;

/**
 * @author kejiefu
 */

public enum Action {

    /**
     * 接收
     */
    ACCEPT,
    /**
     * 重试
     */
    RETRY,
    /**
     * 拒绝
     */
    REJECT;

    private Action() {

    }

}
