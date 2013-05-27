drop table if exists users;
create table users (
    id int auto_increment,
    name varchar(50) not null,
    role int not null default 0,
    last_login_time timestamp default current_timestamp,
    primary key(id),
    unique key(name)
);

insert into users(id, name) values
(1, 'tom'),
(2, 'jerry'),
(3, 'alice'),
(4, 'frank');
