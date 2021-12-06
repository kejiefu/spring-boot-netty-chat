package com.mountain.im.connector.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * @author kejiefu
 */
@Slf4j
public class Netty4Utils {

    public static String getIp() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (Exception ex) {
            log.error("Netty4Utils.getIp:", ex);
        }
        return "";
    }

}
