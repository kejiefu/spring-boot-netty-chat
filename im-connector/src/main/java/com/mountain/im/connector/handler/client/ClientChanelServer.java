package com.mountain.im.connector.handler.client;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/8/2 20:24
 * @Created by kejiefu
 */
@Slf4j
public class ClientChanelServer {

    public static final Map<String, ClientChanel> clientChanelServerMap = new ConcurrentHashMap<>();

    private ClientChanelServer() {

    }

    public static boolean register(String token, ClientChanel clientChanel) {
        if (Strings.isNullOrEmpty(token) || clientChanelServerMap.containsKey(token)) {
            return false;
        }
        clientChanelServerMap.put(token, clientChanel);
        return true;
    }

    public static boolean logout(String token) {
        if (Strings.isNullOrEmpty(token) || !clientChanelServerMap.containsKey(token)) {
            return false;
        }
        clientChanelServerMap.remove(token);
        return true;
    }




}
