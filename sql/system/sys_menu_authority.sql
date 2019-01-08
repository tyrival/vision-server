create table sys_menu_authority
(
  id          varchar(36) not null  constraint sys_menu_authority_pkey  primary key,
  owner_id    varchar(36),
  menu_id     varchar(36),
  create_time     date,
  create_user_id  varchar(50),
  update_time     date,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table sys_menu_authority
is '用户角色表';

comment on column sys_menu_authority.id
is '主键id';

comment on column sys_menu_authority.owner_id
is '所有者id';

comment on column sys_menu_authority.menu_id
is '菜单节点id';

comment on column sys_menu_authority.create_time
is '创建时间';

comment on column sys_menu_authority.create_user_id
is '创建人';

comment on column sys_menu_authority.update_time
is '修改时间';

comment on column sys_menu_authority.update_user_id
is '修改人';

comment on column sys_menu_authority.del_flag
is '删除标志';


