create table ds_file
(
  id              varchar(36) not null  constraint ds_file_pkey  primary key,
  name  varchar(100),
  attachment_id  varchar(36),
  type  integer,
  create_time     timestamp,
  create_user_id  varchar(50),
  update_time     timestamp,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table ds_file
is '文件数据源';

comment on column ds_file.name is '名称';
comment on column ds_file.attachment_id is '附件ID';
