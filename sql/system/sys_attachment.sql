create table sys_attachment
(
  id              varchar(36)  not null  constraint file_pkey  primary key,
  name            varchar(500) not null,
  full_name       varchar(500) not null,
  extension_name  varchar(100) not null,
  size            integer      not null,
  absolute_path   varchar(2000)  not null,
  relative_path   varchar(2000) not null,
  create_time     date,
  create_user_id  varchar(50),
  update_time     date,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table sys_attachment
is '文件表';

comment on column sys_attachment.id
is '主键id';

comment on column sys_attachment.name
is '文件名';

comment on column sys_attachment.full_name
is '文件全名';

comment on column sys_attachment.extension_name
is '后缀名';

comment on column sys_attachment.size
is '大小';

comment on column sys_attachment.absolute_path
is '储存路径';

comment on column sys_attachment.relative_path
is '相对路径';

comment on column sys_attachment.create_time
is '创建时间';

comment on column sys_attachment.create_user_id
is '创建人';

comment on column sys_attachment.update_time
is '修改时间';

comment on column sys_attachment.update_user_id
is '修改人';

comment on column sys_attachment.del_flag
is '删除标志';

