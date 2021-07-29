package com.mountain.im.connector.handler.transfer;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.mountain.im.connector.factory.TransferFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 初始化
 *
 * @author kejiefu
 * @Date 2020/12/18 15:30
 * @Created by kejiefu
 */
@Component
@Slf4j
public class TransferInitialize {

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddress;

    @Value("${transfer.name}")
    private String transferName;

    /**
     * 初始化的时候绑定transfer的服务器
     */
    @PostConstruct
    private void initialize() {
        try {
            //获取nacos服务
            NamingService namingService = NamingFactory.createNamingService(serverAddress);
            //服务列表
            List<Instance> instanceList = namingService.selectInstances(transferName, true);
            if (!CollectionUtils.isEmpty(instanceList)) {
                for (Instance instance : instanceList) {
                    //初始化transfer的渠道
                    TransferFactory.getInstance().newChannel(instance.getIp(), instance.getPort());
                }
            }
        } catch (Exception ex) {
            log.error("selectInstances:", ex);
        }
    }


}
