/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     3/9/2020 10:27:06 PM                         */
/*==============================================================*/
drop table if exists note;

drop table if exists user;

/*==============================================================*/
/* Table: note                                                  */
/*==============================================================*/
create table note
(
   note_id              int not null auto_increment,
   user_id              int not null,
   title                varchar(30),
   text                 varchar(50),
   primary key (note_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int not null auto_increment,
   username             varchar(20) not null,
   primary key (user_id)
);

alter table note add constraint FK_has foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

