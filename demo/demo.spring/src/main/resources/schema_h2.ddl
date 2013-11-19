create table account (
  id int not null auto_increment,
  name varchar not null comment '账户名',
  password varchar not null comment '账户密码',
  created datetime comment '创建时间',
  primary key(id),
  unique uniq_name (name)
) comment='账户表' ;

create table account_log (
  id int not null auto_increment,
  account_id int not null comment '账户ID',
  field varchar not null comment '数据变更属性',
  origin varchar comment '原数据',
  value  varchar comment '新数据',
  updated datetime not null comment '变更时间',
  primary key(id),
  index idx_account_id(account_id)
) comment='账户变更记录表' ;