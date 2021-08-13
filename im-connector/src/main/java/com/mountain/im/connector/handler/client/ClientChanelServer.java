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

    public static boolean register(Long userId, ClientChanel clientChanel) {
        if (Objects.nonNull(userId) || clientChanelServerMap.containsKey(userId)) {
            return false;
        }
        clientChanelServerMap.put(userId, clientChanel);
        return true;
    }

    public static boolean logout(Long userId) {
        if (Objects.nonNull(userId) || !clientChanelServerMap.containsKey(userId)) {
            return false;
        }
        clientChanelServerMap.remove(userId);
        return true;
    }

    public static boolean updateDate(Long userId) {
        ClientChanel clientChanel = clientChanelServerMap.get(userId);
        clientChanel.setDate(new Date());
        return true;
    }


}
