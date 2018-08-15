create table exercise
(
  id          int auto_increment
    primary key,
  title       varchar(255) null,
  description text         null
);

create table user_group
(
  id   int auto_increment
    primary key,
  name varchar(255) null
);

create table users
(
  id            bigint auto_increment
    primary key,
  username      varchar(255) null,
  email         varchar(255) null,
  password      varchar(255) null,
  user_group_id int          not null,
  constraint users_ibfk_1
  foreign key (user_group_id) references user_group (id)
);

create table solution
(
  id          int auto_increment
    primary key,
  created     datetime default CURRENT_TIMESTAMP null,
  updated     datetime                           null
  on update CURRENT_TIMESTAMP,
  description text                               null,
  exercise_id int                                not null,
  users_id    bigint                             not null,
  constraint solution_ibfk_1
  foreign key (exercise_id) references exercise (id),
  constraint solution_ibfk_2
  foreign key (users_id) references users (id)
);

create index exercise_id
  on solution (exercise_id);

create index users_id
  on solution (users_id);

create index user_group_id
  on users (user_group_id);


