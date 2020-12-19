package com.mountain.imtransfer.listener;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.mountain.imtransfer.factory.TransferFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2020/12/18 16:13
 * @Created by kejiefu
 */
@Component
@Slf4j
public class TransferListener {

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddress;

    @Value("${transfer.name}")
    private String transferName;

    /**
     * 初始化的时候绑定transfer的服务器
     */
    @PostConstruct
    public void initialize() {
        log.info("TransferListener.initialize...");
        try {
            //获取nacos服务
            NamingService namingService = NamingFactory.createNamingService(serverAddress);
            //服务列表
            List<Instance> instanceList = namingService.selectInstances(transferName, true);
            log.info("instanceList:{}", instanceList);
            initChannel(instanceList);
            //监听服务下的实例列表变化,就是还剩下多少健康实例
            namingService.subscribe(transferName, event -> {
                if (event instanceof NamingEvent) {
                    log.info("监听服务下的实例列表变化，{}，{}", ((NamingEvent) event).getServiceName(), ((NamingEvent) event).getInstances());
                    List<Instance> instanceList1 = ((NamingEvent) event).getInstances();
                    initChannel(instanceList1);
                }
            });
        } catch (Exception ex) {
            log.error("TransferListener.initialize:", ex);
        }
    }

    private void initChannel(List<Instance> instanceList) {
        if (!CollectionUtils.isEmpty(instanceList)) {
            for (Instance instance : instanceList) {
                //初始化transfer的渠道
                try {
                    TransferFactory.getInstance().newChannel(instance.getIp(), instance.getPort());
                } catch (InterruptedException e) {
                    log.error("newChannel:", e);
                }
            }
        } else {
            log.error("initChannel.newChannel为空");
        }
    }

}
