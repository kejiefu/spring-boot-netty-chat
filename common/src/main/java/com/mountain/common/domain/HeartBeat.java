package com.mountain.common.domain;

import lombok.Data;

/**
 * 心跳消息
 *
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/21 19:51
 * @Created by kejiefu
 */
@Data
public class HeartBeat {

    /**
     * 内容
     */
    private String content;

    /**
     * 地址信息
     */
    private String address;


}
