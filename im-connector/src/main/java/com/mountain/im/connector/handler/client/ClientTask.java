package com.mountain.im.connector.handler.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/8/12 18:11
 * @Created by kejiefu
 */
@Slf4j
@Component
public class ClientTask {

    /**
     * 检查连接,超时将其去除
     */
    @Scheduled(cron = "0/50 * * * * ?")
    public void checkHeart() {
        log.info("---------------------------ClientTask start-----------------------------------------------");
        Iterator<ClientChanel> iter = ClientChanelServer.clientChanelServerMap.values().iterator();
        while (iter.hasNext()) {
            ClientChanel next = iter.next();
            long diff = System.currentTimeMillis() - next.getDate().getTime();
            long value = diff / 1000;
            if (value > 1) {
                ClientChanelServer.logout(next.getUserId());
            }
        }
        log.info("--------------------------- ClientTask end-----------------------------------------------");
    }


}
