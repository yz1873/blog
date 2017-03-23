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
`summary` VARCHAR(500) COMMENT '文章简介',
`content` VARCHAR(20000) NOT NULL COMMENT '文章内容',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (article_id)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='文章表';

INSERT INTO
  article(author_id,title,summary,content)
VALUES
  (100,'2月大兴、房山成北京楼市热点 共成交2819套','2月大兴、房山成北京楼市热点 共成交2819套','凭借新产业的引入、区域配套的提升、轨道交通的建设，北京大兴区、房山区楼市开始快速发展。');

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


CREATE TABLE role(
`role_id` BIGINT NOT NULL COMMENT '角色id',
`role_name` VARCHAR(30) NOT NULL COMMENT '角色名称',
PRIMARY KEY(role_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

INSERT INTO
  role(role_id,role_name)
VALUES
  (1,'admin'),(2,'manager'),(3,'normal');



CREATE TABLE author_role(
`author_role_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '作者_角色id',
`author_id` BIGINT NOT NULL COMMENT '作者id',
`role_id` BIGINT NOT NULL COMMENT '角色id',
PRIMARY KEY(author_role_id),
FOREIGN KEY(author_id) REFERENCES author(author_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(role_id) REFERENCES role(role_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='作者_角色表';

INSERT INTO
  author_role(author_id,role_id)
VALUES
  (100,1);


CREATE TABLE permissions(
`permission_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '权限id',
`role_id` BIGINT NOT NULL COMMENT '角色id',
`permission_name` VARCHAR(30) NOT NULL COMMENT '权限名称',
PRIMARY KEY(permission_id),
FOREIGN KEY(role_id) REFERENCES role(role_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='权限表';

INSERT INTO
  permissions(role_id,permission_name)
VALUES
  (1,'add'),(2,'delete');