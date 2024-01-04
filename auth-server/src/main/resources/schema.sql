-- auth-koyeb/auth-server/src/main/resources/schema.sql

-- users
create table if not exists users
(
    username varchar(200) not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);
-- authorities
create table if not exists authorities
(
    username  varchar(200) not null,
    authority varchar(50)  not null,
    constraint fk_authorities_users foreign key (username) references users (username),
    constraint username_authority UNIQUE (username, authority)
);
create unique index if not exists  ix_auth_username on authorities (username,authority);

-- groups
create table if not exists  groups (
	id bigint generated by default as identity(start with 1) primary key,
	group_name varchar(50) not null
);

create table if not exists  group_authorities (
	group_id bigint not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table if not exists  group_members (
	id bigint generated by default as identity(start with 1) primary key,
	username varchar(50) not null,
	group_id bigint not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id)
);
