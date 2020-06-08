create table sys_role
(
  id         varchar(36)  not null  constraint sys_role_pkey  primary key,
  name       varchar(100) not null,
  comment      varchar(500),
  create_time     timestamp,
  create_user_id  varchar(50),
  update_time     timestamp,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table sys_role
is '角色表';

comment on column sys_role.id
is '主键id';

comment on column sys_role.name
is '角色名';

comment on column sys_role.comment
is '说明';

comment on column sys_role.create_time
is '创建时间';

comment on column sys_role.create_user_id
is '创建人';

comment on column sys_role.update_time
is '修改时间';

comment on column sys_role.update_user_id
is '修改人';

comment on column sys_role.del_flag
is '删除标志';

