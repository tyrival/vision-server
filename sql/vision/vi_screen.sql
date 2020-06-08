create table vi_screen
(
  id              varchar(36) not null  constraint vi_screen_pkey  primary key,
  name    varchar(500),
  cover   varchar(4000),
  base    text,
  sequence    text,
  components  text,
  state   integer      not null,
  create_time     timestamp,
  create_user_id  varchar(50),
  update_time     timestamp,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table vi_screen
is 'Vision大屏';

comment on column vi_screen.name is '名称';
comment on column vi_screen.cover is '封面';
comment on column vi_screen.base is '基础配置';
comment on column vi_screen.sequence is '组件顺序';
comment on column vi_screen.components is '组件';
comment on column vi_screen.state is '状态';



