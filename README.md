# spring-boot-netty-chat
可伸缩性/可扩展性基于netty开发分布式的聊天系统。

技术架构：spring cloud，nacos集群：版本1.4.0，netty，rabbitmq，mysql版本：8.0，分库分表

connector：模块用于维持用户的长链接。

transfer：作用是将消息在多个connector之间转发。

gateway：网关，处理http信息，例如注册，登录，查询聊天记录

user-service：用户信息

chat-service：存储聊天信息，查询聊天信息，分库分表