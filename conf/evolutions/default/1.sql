# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  id                        varchar(255) not null,
  course_name               varchar(255),
  constraint pk_course primary key (id))
;

create table study_group (
  id                        bigint not null,
  meet_time                 timestamp,
  course_level              varchar(255),
  location                  varchar(255),
  topics                    varchar(255),
  year                      integer,
  constraint pk_study_group primary key (id))
;

create sequence course_seq;

create sequence study_group_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

drop table if exists study_group;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;

drop sequence if exists study_group_seq;

