-- =====================================================================
-- 仿酷家乐 Demo · MySQL 建库建表脚本
-- ---------------------------------------------------------------------
-- 表结构与 design-service 的 JPA 实体一一对应（kjl_ 前缀）
-- 执行方式（二选一）：
--   root 空密码：  mysql -uroot < init.sql
--   root 有密码：  mysql -uroot -p < init.sql
--   或：登录 mysql 后执行  source init.sql
-- 说明：脚本可重复执行（幂等），已含演示数据。
-- =====================================================================

CREATE DATABASE IF NOT EXISTS `kujiale_demo`
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE `kujiale_demo`;

-- ---------------------------------------------------------------------
-- 用户表
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `kjl_user` (
  `id`         BIGINT       NOT NULL AUTO_INCREMENT,
  `username`   VARCHAR(255) DEFAULT NULL,
  `nickname`   VARCHAR(255) DEFAULT NULL,
  `avatar`     VARCHAR(255) DEFAULT NULL,
  `created_at` DATETIME(6)  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- 户型表
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `kjl_house_type` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(255) DEFAULT NULL,
  `area`        DOUBLE       DEFAULT NULL,
  `layout_json` TEXT,
  `created_at`  DATETIME(6)  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- 设计方案表
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `kjl_design` (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT,
  `user_id`       BIGINT       DEFAULT NULL,
  `house_type_id` BIGINT       DEFAULT NULL,
  `title`         VARCHAR(255) DEFAULT NULL,
  `style`         VARCHAR(255) DEFAULT NULL,
  `prompt`        TEXT,
  `plan_json`     TEXT,
  `thumbnail`     VARCHAR(255) DEFAULT NULL,
  `status`        VARCHAR(255) DEFAULT NULL,
  `created_at`    DATETIME(6)  DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- AI 对话历史表
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `kjl_design_message` (
  `id`         BIGINT       NOT NULL AUTO_INCREMENT,
  `session_id` VARCHAR(255) DEFAULT NULL,
  `role`       VARCHAR(255) DEFAULT NULL,
  `content`    TEXT,
  `created_at` DATETIME(6)  DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_session_id` (`session_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- 演示数据（幂等）
-- ---------------------------------------------------------------------
INSERT INTO `kjl_user` (`username`, `nickname`, `avatar`, `created_at`)
SELECT 'demo', '演示用户', '', NOW(6)
WHERE NOT EXISTS (SELECT 1 FROM `kjl_user` WHERE `username` = 'demo');

INSERT INTO `kjl_house_type` (`name`, `area`, `layout_json`, `created_at`)
SELECT * FROM (
  SELECT '三室两厅', 90.0, '{"rooms":[{"name":"客厅","x":0,"z":0,"w":4,"d":3},{"name":"厨房","x":4,"z":0,"w":2,"d":3},{"name":"卫生间","x":6,"z":0,"w":2,"d":3},{"name":"主卧","x":0,"z":3,"w":2.7,"d":3},{"name":"次卧","x":2.7,"z":3,"w":2.7,"d":3},{"name":"书房","x":5.4,"z":3,"w":2.6,"d":3}]}', NOW(6)
  UNION ALL
  SELECT '两室一厅', 70.0, '{"rooms":[{"name":"客厅","x":0,"z":0,"w":4,"d":3},{"name":"厨房","x":4,"z":0,"w":2.5,"d":3},{"name":"卫生间","x":6.5,"z":0,"w":1.5,"d":3},{"name":"主卧","x":0,"z":3,"w":4,"d":3},{"name":"次卧","x":4,"z":3,"w":4,"d":3}]}', NOW(6)
  UNION ALL
  SELECT '一室一厅', 45.0, '{"rooms":[{"name":"客厅","x":0,"z":0,"w":5,"d":3.5},{"name":"卧室","x":0,"z":3.5,"w":3.5,"d":2.5},{"name":"厨房","x":5,"z":0,"w":3,"d":3},{"name":"卫生间","x":3.5,"z":3.5,"w":2,"d":2.5}]}', NOW(6)
) AS t
WHERE NOT EXISTS (SELECT 1 FROM `kjl_house_type`);
