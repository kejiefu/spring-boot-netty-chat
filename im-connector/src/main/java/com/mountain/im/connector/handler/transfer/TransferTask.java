package com.mountain.im.connector.handler.transfer;

import com.mountain.im.connector.factory.TransferFactory;
import com.mountain.im.connector.transfer.TransferChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author kejiefu
 */
@Slf4j
@Component
public class TransferTask {

    /**
     * 检查transfer心跳
     */
    @Scheduled(cron = "0/50 * * * * ?")
    public void checkHeart() {
        log.info("---------------------------checkHeart start-----------------------------------------------");
        Iterator<TransferChannel> iter = TransferFactory.getInstance().channelsMap.values().iterator();
        while (iter.hasNext()) {
            TransferChannel next = iter.next();
            if (next.isHeatBeatStop(System.currentTimeMillis())) {
                log.error("心跳超时去除,{}", next);
                next.disconnect();
                iter.remove();
            }
        }
        log.info("---------------------------checkHeart end-----------------------------------------------");
    }

}
