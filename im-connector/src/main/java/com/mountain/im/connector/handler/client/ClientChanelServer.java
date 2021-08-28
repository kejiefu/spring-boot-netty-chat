package com.mountain.im.connector.handler.client;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/8/2 20:24
 * @Created by kejiefu
 */
@Slf4j
public class ClientChanelServer {

    public static final Map<Long, ClientChanel> clientChanelServerMap = new ConcurrentHashMap<>();

    private ClientChanelServer() {

    }

    /**
     * 将其存在的channel remove掉，换成新的，每个用户志允许存在一个登录
     *
     * @param userId
     * @param clientChanel
     * @return
     */
    public static boolean register(Long userId, ClientChanel clientChanel) {
        if (Objects.nonNull(userId) || clientChanelServerMap.containsKey(userId)) {
            clientChanelServerMap.remove(userId);
        }
        clientChanelServerMap.put(userId, clientChanel);
        return true;
    }

    public static void logout(Long userId) {
        if (Objects.nonNull(userId) || !clientChanelServerMap.containsKey(userId)) {
            return;
        }
        clientChanelServerMap.remove(userId);
    }

    public static void updateDate(Long userId) {
        ClientChanel clientChanel = clientChanelServerMap.get(userId);
        clientChanel.setDate(new Date());
    }


}
