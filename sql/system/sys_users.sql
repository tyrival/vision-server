create table sys_users
(
  id         varchar(36)  not null  constraint sys_users_pkey  primary key,
  account    varchar(255) not null,
  password   varchar(255) not null,
  name       varchar(100) not null,
  user_state integer      not null,
  tel        varchar(50),
  email      varchar(255),
  create_time     timestamp,
  create_user_id  varchar(50),
  update_time     timestamp,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table sys_users
is '用户表';

comment on column sys_users.id
is '主键id';

comment on column sys_users.account
is '用户名';

comment on column sys_users.password
is '密码';

comment on column sys_users.name
is '姓名';

comment on column sys_users.user_state
is '状态';

comment on column sys_users.tel
is '电话';

comment on column sys_users.email
is 'Email';

comment on column sys_users.create_time
is '创建时间';

comment on column sys_users.create_user_id
is '创建人';

comment on column sys_users.update_time
is '修改时间';

comment on column sys_users.update_user_id
is '修改人';

comment on column sys_users.del_flag
is '删除标志';

insert into sys_users (id, account, password, name, user_state, tel, email, create_time, create_user_id, update_time, update_user_id, del_flag) values (1, 'admin', '123', '系统管理员', 1, '', '', null, null, null, null, 0);


