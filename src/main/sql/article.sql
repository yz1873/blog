-- 数据库初始化脚本
-- 创建数据库
CREATE DATABASE blog;
-- 使用数据库
use blog;
-- 创建秒杀库存表
-- 数据引擎只有innodb才支持事务
CREATE TABLE article(
`article_id` BIGINT NOT NUll AUTO_INCREMENT COMMENT '文章ID',
`author_id` BIGINT NOT NULL COMMENT '作者id',
`title` VARCHAR(120) NOT NULL COMMENT '文章标题',
`content` VARCHAR(2000) NOT NULL COMMENT '文章内容',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (article_id)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='文章表';

INSERT INTO
  article(author_id,title,content)
VALUES
  (100,'2月大兴、房山成北京楼市热点 共成交2819套','凭借新产业的引入、区域配套的提升、轨道交通的建设，北京大兴区、房山区楼市开始快速发展。');

CREATE TABLE author(
`author_id` BIGINT NOT NULL COMMENT '作者id',
`author_name` VARCHAR(30) NOT NULL COMMENT '作者名字',
`username` VARCHAR(30) NOT NULL COMMENT '作者账号名称',
`password` VARCHAR(30) NOT NULL COMMENT '作者账号密码',
PRIMARY KEY(author_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作者表';

INSERT INTO
  author(author_id,author_name,username,password)
VALUES
  (100,'张宇','rabbiton1989','890924');