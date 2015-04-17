/* Drop tables - possibilitie to execute script X times */
drop table if exists users;
drop table if exists roles;
drop table if exists user_roles;

/* Creating tables */

create table if not exists users (
  id bigint unsigned not null auto_increment,
  username varchar(100) not null,
  password binary(60) not null,
  role bigint unsigned not null,
  constraint pk_users primary key(id),
  unique(username)
);
/*
create table if not exists roles (
  id bigint unsigned not null auto_increment,
  role varchar(100) not null,
  constraint pk_roles primary key(id),
  unique(role)
);

create table if not exists user_roles (
  user_id bigint unsigned not null,
  role_id bigint unsigned not null,
  unique(user_id, role_id),
  index(user_id)
);

/* Entries */