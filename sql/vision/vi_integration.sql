create table vi_integration
(
  id              varchar(36) not null  constraint vi_integration_pkey  primary key,
  name    varchar(500),
  description   varchar(4000),
  cover   varchar(4000),
  layout  varchar(200),
  base    text,
  navigation    text,
  state   integer      not null,
  create_time     date,
  create_user_id  varchar(50),
  update_time     date,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table vi_integration
is '可视化系统';

comment on column vi_integration.id is 'ID';
comment on column vi_integration.name is '名称';
comment on column vi_integration.description is '描述';
comment on column vi_integration.cover is '封面';
comment on column vi_integration.layout is '布局';
comment on column vi_integration.base is '基础配置';
comment on column vi_integration.navigation is '菜单项';
comment on column vi_integration.state is '状态';

