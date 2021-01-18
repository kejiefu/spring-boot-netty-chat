package com.mountain.chat.service.util;

import com.baomidou.mybatisplus.core.toolkit.Sequence;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2020/12/31 15:00
 * @Created by kejiefu
 */
@SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
public class SequenceUtils {

    public final static Sequence sequence = new Sequence();

    public static long getId() {
        return sequence.nextId();
    }


}
