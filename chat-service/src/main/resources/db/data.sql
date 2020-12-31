CREATE TABLE `t_chat_record_0` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint DEFAULT NULL COMMENT '发话的用户id',
  `to_user_id` bigint DEFAULT NULL COMMENT '接收话的用户id',
  `content` varchar(1024) DEFAULT NULL COMMENT '聊天内容',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户聊天记录';