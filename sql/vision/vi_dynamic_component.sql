create table vi_dynamic_component
(
  id            varchar(36) not null  constraint vi_dynamic_component_pkey  primary key,
  text          varchar(500),
  category      varchar(100),
  name          integer     not null,
  image         varchar(500),
  config        text,
  create_time     date,
  create_user_id  varchar(50),
  update_time     date,
  update_user_id  varchar(50),
  del_flag        integer default 0
);

comment on table vi_dynamic_component
is 'Vision用户自定义组件';

comment on column vi_dynamic_component.id is 'ID';
comment on column vi_dynamic_component.text is '名称';
comment on column vi_dynamic_component.category is '分类';
comment on column vi_dynamic_component.name is '动态组件类型';
comment on column vi_dynamic_component.image is '图片';
comment on column vi_dynamic_component.config is '配置';