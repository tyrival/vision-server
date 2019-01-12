create table ds_database
(
  id              varchar(36) not null  constraint ds_database_pkey  primary key,
  name  varchar(100),
  host  varchar(2000),
  port  varchar(10),
  db  varchar(100),
  user_name  varchar(100),
  password  varchar(255),
  type  integer,
  create_time     date,
  create_user_id  varchar(50),
  update_time     date,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table ds_database
is '数据库';

comment on column ds_database.name is '名称';
comment on column ds_database.host is '域名';
comment on column ds_database.port is '端口';
comment on column ds_database.db is '数据库';
comment on column ds_database.user_name is '用户名';
comment on column ds_database.password is '密码';
comment on column ds_database.type is '类型';

