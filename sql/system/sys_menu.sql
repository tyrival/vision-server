create table sys_menu
(
  id         varchar(36)  not null  constraint sys_menu_pkey  primary key,
  name       varchar(100) not null,
  parent_id  varchar(36),
  icon       varchar(100),
  url        varchar(2000),
  hash       varchar(100),
  sort       integer,
  create_time     date,
  create_user_id  varchar(50),
  update_time     date,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table sys_menu
is '菜单表';

comment on column sys_menu.id
is '主键id';

comment on column sys_menu.name
is '节点名';

comment on column sys_menu.parent_id
is '父节点id';

comment on column sys_menu.icon
is '图标';

comment on column sys_menu.url
is 'url';

comment on column sys_menu.hash
is 'hash路由';

comment on column sys_menu.sort
is '排序';

comment on column sys_menu.create_time
is '创建时间';

comment on column sys_menu.create_user_id
is '创建人';

comment on column sys_menu.update_time
is '修改时间';

comment on column sys_menu.update_user_id
is '修改人';

comment on column sys_menu.del_flag
is '删除标志';


