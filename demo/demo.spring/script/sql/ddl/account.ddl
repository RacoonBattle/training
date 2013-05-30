create table account (
  id int not null auto_increment,
  name  varchar(50) not null comment '账户名',
  password varchar(10) not null comment '账户密码',
  created timestamp comment '创建时间',
  primary key(id),
  unique uniq_name (name)
) engine=InnoDB default charset utf8 comment='账户表' ;