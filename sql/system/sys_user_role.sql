create table sys_user_role
(
  id        varchar(36) not null  constraint sys_user_role_pkey  primary key,
  user_id   varchar(36),
  role_id   varchar(36),
  create_time     timestamp,
  create_user_id  varchar(50),
  update_time     timestamp,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table sys_user_role
is '用户角色表';

comment on column sys_user_role.id
is '主键id';

comment on column sys_user_role.user_id
is '用户id';

comment on column sys_user_role.role_id
is '角色id';

comment on column sys_user_role.create_time
is '创建时间';

comment on column sys_user_role.create_user_id
is '创建人';

comment on column sys_user_role.update_time
is '修改时间';

comment on column sys_user_role.update_user_id
is '修改人';

comment on column sys_user_role.del_flag
is '删除标志';

