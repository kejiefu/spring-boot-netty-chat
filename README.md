# spring-boot-netty-chat
#### 一套高可用、易伸缩、高并发的IM群聊、单聊架构聊天系统。



#### 架构图：

![](https://github.com/kejiefu/spring-boot-netty-chat/blob/main/chat-flow.jpg)



技术架构：spring cloud，nacos集群：版本1.4.0，netty，rabbitmq，mysql版本：8.0，分库分表

connector：模块用于维持用户端的长链接。

transfer：模块是将消息在不同的机器之间转发，使服务可以水平扩展。为了满足实时转发，transfer需要和每台connector机器都保持长链接。

gateway：网关，处理http信息，例如注册，登录，查询聊天记录

user-service：用户信息

chat-service：存储聊天信息，查询聊天信息，分库分表

rabbitmq：解耦聊天的信息存储

redis：存储用户当前在线状态与对应的connector地址