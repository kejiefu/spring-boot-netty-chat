package com.mountain.chatservice.config;

import com.baomidou.mybatisplus.core.toolkit.Sequence;
import io.shardingsphere.core.keygen.KeyGenerator;

/**
 * <p>
 * 自定义雪花算法，替换 DefaultKeyGenerator，避免DefaultKeyGenerator生成的id大几率是偶数
 * </p>
 *
 * @description: 自定义雪花算法，替换 DefaultKeyGenerator，避免DefaultKeyGenerator生成的id大几率是偶数
 */
public class CustomSnowflakeKeyGenerator implements KeyGenerator {

    private Sequence sequence;

    public CustomSnowflakeKeyGenerator(Sequence sequence) {
        this.sequence = sequence;

    }

    @Override
    public Number generateKey() {
        return sequence.nextId();
    }
}
