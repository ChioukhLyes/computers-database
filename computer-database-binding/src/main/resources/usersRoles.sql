/* Drop tables - possibilitie to execute script X times */
drop table if exists users;


/* Creating tables */

create table if not exists users (
  id bigint unsigned not null auto_increment,
  username varchar(100) not null,
  password binary(60) not null,
  role bigint unsigned not null,
  constraint pk_users primary key(id),
  unique(username)
);
