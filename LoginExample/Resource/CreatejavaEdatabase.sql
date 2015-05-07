/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/5/7 13:30:11                            */
/*==============================================================*/
drop table if exists JournalArticle;
drop table if exists AuthorArticle;
drop table if exists ArticleReview;
drop table if exists Article;
drop table if exists Author;
drop table if exists Editor;
drop table if exists Journal;
drop table if exists Reviewer;
drop table if exists User;

/*==============================================================*/
/* Table: Article                                               */
/*==============================================================*/
create table Article
(
   articlename          varchar(100) not null,
   keywords             varchar(256),
   abstract             longtext,
   url                  longtext,
   domain               varchar(50),
   uploaddate           date,
   ispublish            smallint,
   affiliations         varchar(256),
   primary key (articlename)
);

/*==============================================================*/
/* Table: ArticleReview                                         */
/*==============================================================*/
create table ArticleReview
(
   reviewername         varchar(50),
   articlename          varchar(100),
   reviewstatus         varchar(20)
);

/*==============================================================*/
/* Table: Author                                                */
/*==============================================================*/
create table Author
(
   authorname           varchar(50) not null,
   email                varchar(100),
   submitstate          int,
   ID                   int,
   primary key (authorname)
);

/*==============================================================*/
/* Table: AuthorArticle                                         */
/*==============================================================*/
create table AuthorArticle
(
   authorname           varchar(50),
   articlename          varchar(100)
);

/*==============================================================*/
/* Table: Editor                                                */
/*==============================================================*/
create table Editor
(
   editorname           varchar(50) not null,
   ID                   int,
   primary key (editorname)
);

/*==============================================================*/
/* Table: Journal                                               */
/*==============================================================*/
create table Journal
(
   journalID            int not null,
   journalname          varchar(100),
   version              varchar(50),
   publishtime          date,
   primary key (journalID)
);

/*==============================================================*/
/* Table: JournalArticle                                        */
/*==============================================================*/
create table JournalArticle
(
   articlename          varchar(100),
   journalID            int
);

/*==============================================================*/
/* Table: Reviewer                                              */
/*==============================================================*/
create table Reviewer
(
   reviewername         varchar(50) not null,
   selectednum          int,
   ID                   int,
   primary key (reviewername)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   ID                   int not null,
   username             varchar(50),
   password             varchar(50),
   role                 int,
   primary key (ID)
);

alter table ArticleReview add constraint FK_Reference_4 foreign key (reviewername)
      references Reviewer (reviewername) on delete restrict on update restrict;

alter table ArticleReview add constraint FK_Reference_5 foreign key (articlename)
      references Article (articlename) on delete restrict on update restrict;

alter table Author add constraint FK_Reference_1 foreign key (ID)
      references User (ID) on delete restrict on update restrict;

alter table AuthorArticle add constraint FK_Reference_8 foreign key (authorname)
      references Author (authorname) on delete restrict on update restrict;

alter table AuthorArticle add constraint FK_Reference_9 foreign key (articlename)
      references Article (articlename) on delete restrict on update restrict;

alter table Editor add constraint FK_Reference_3 foreign key (ID)
      references User (ID) on delete restrict on update restrict;

alter table JournalArticle add constraint FK_Reference_6 foreign key (journalID)
      references Journal (journalID) on delete restrict on update restrict;

alter table JournalArticle add constraint FK_Reference_7 foreign key (articlename)
      references Article (articlename) on delete restrict on update restrict;

alter table Reviewer add constraint FK_Reference_2 foreign key (ID)
      references User (ID) on delete restrict on update restrict;

