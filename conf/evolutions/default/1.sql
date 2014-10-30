# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  id                        varchar(255) not null,
  course_name               varchar(255),
  constraint pk_course primary key (id))
;

create sequence course_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;

