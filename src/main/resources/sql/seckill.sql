-- ------------
-- 秒杀数据库  |
-- ------------
DROP DATABASE IF EXISTS `seckill`;
CREATE DATABASE `seckill` DEFAULT CHARACTER SET utf8;
USE `seckill`;

-- 秒杀库存表
CREATE TABLE `t_seckill` (
    `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
    `name` VARCHAR(220) NOT NULL COMMENT '商品名称',
    `number` INT NOT NULL COMMENT '库存数量',
    `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
    `end_time` TIMESTAMP NOT NULL COMMENT  '秒杀结束时间',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建活动时间',
    PRIMARY KEY (`seckill_id`),
    INDEX idx_start_time(`start_time`),
    INDEX idx_end_time(`end_time`),
    INDEX idx_create_time(`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';


-- 初始化数据
INSERT INTO
    t_seckill(`name`, `number`, `start_time`, `end_time`)
VALUES
    ('2000元秒杀小米笔记本', 100, '2019-01-01 00:00:00', '2019-01-01 01:00:00'),
    ('1500元秒杀小米8', 200, '2019-01-01 00:00:00', '2019-01-01 01:00:00'),
    ('3000元秒杀iPhone8', 300, '2019-01-01 00:00:00', '2019-01-01 01:00:00'),
    ('2000元秒杀iPad', 500, '2019-01-01 00:00:00', '2019-01-01 01:00:00');


-- 秒杀成功明细表、用户登录认证表
CREATE TABLE `t_success_killed`(
    `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
    `user_phone` VARCHAR(11) NOT NULL COMMENT '用户手机号',
    `status` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识：-1:无效，0:成功，1:已付款',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`seckill_id`, `user_phone`),  /* 联合组建 */
    INDEX idx_create_time(`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';