create table account_log (
  id int not null auto_increment,
  account_id int not null comment '账户ID',
  field varchar(10) not null comment '数据变更属性',
  origin varchar(50) comment '原数据',
  value  varchar(50) comment '新数据',
  updated timestamp not null default now() comment '变更时间',
  primary key(id),
  index idx_account_id(account_id)
) engine=InnoDB default charset utf8 comment='账户变更记录表' ;
