create table ds_api
(
  id              varchar(36) not null  constraint ds_api_pkey  primary key,
  name  varchar(100),
  url  varchar(36),
  protocol  integer,
  type      integer,
  create_time     timestamp,
  create_user_id  varchar(50),
  update_time     timestamp,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table ds_api
is 'API数据源';

comment on column ds_api.name is '名称';
comment on column ds_api.url is 'URL';
comment on column ds_api.protocol is '网络协议';
