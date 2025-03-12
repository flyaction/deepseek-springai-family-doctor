CREATE TABLE `chat_record` (
   `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '表的主键，保证唯一',
   `content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '聊天内容，用户发送或者回复消息',
   `chat_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '会话类型，user-用户类型的消息；bot-AI生成的消息类型',
   `chat_time` datetime NOT NULL COMMENT '当前记录发生的时间，消息产生的时间',
   `family_member` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '家庭成员的名字。用于保存聊天记录的归属。',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;