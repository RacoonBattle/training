drop table if exists account;
create table account (
  id int not null auto_increment,
  name  varchar(50) not null comment '账户名',
  password varchar(10) not null comment '账户密码',
  role int not null default 0 comment '用户角色',
  last_login_time timestamp comment '最后登录时间',
  created timestamp comment '创建时间',
  primary key(id),
  unique uniq_name (name)
) engine=InnoDB default charset utf8 comment='账户表' ;


insert into account (name, password, role, last_login_time, created) values
('alice', '123456', 0, now(), now()),
('frank', 'abcedf', 0, now(), now()),
('steve', '123456', 1, now(), now());