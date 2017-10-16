Drop table Directory_Place;
Drop table Directory;
Drop table Place;
Drop table UserInfo;
Drop table UserProj;
Drop table UserType;

create table UserProj (
id_user BIGINT not null primary key generated always as identity (start with 1, increment by 1),
username varchar(20) not null,
password varchar(32) not null,
fk_usertype BIGINT not null
);

create table UserInfo(
id_userinfo BIGINT not null primary key generated always as identity (start with 1, increment by 1),
email varchar(150),
fullname varchar(100),
birthday date not null
);

create table UserType(
id_usertype BIGINT not null primary key generated always as identity (start with 1, increment by 1),
title varchar(20)
);

ALTER TABLE UserInfo
ADD FOREIGN KEY(id_userinfo)
REFERENCES UserProj (id_user)
ON DELETE CASCADE;

ALTER TABLE UserProj
ADD FOREIGN KEY(fk_usertype)
REFERENCES UserType(id_usertype)
ON DELETE CASCADE;

create table Directory (
id_directory BIGINT not null primary key generated always as identity (start with 1, increment by 1),
name_directory varchar (140) not null,
fk_userProj BIGINT not null

);

create table Place (
id_place BIGINT not null primary key generated always as identity (start with 1, increment by 1),
id_API varchar (140) not null,
name_API varchar (140) not null,
popularity int not null
);


create table Directory_Place (
fk_idDirectory BIGINT not null,
fk_idPlace BIGINT not null,
constraint id_DirectoryPlace primary key (fk_idDirectory,fk_idPlace)
);

ALTER TABLE directory_place
ADD FOREIGN KEY(fk_idDirectory)
REFERENCES directory(id_directory)
ON DELETE CASCADE;

ALTER TABLE directory_place
ADD FOREIGN KEY(fk_idPlace)
REFERENCES place(id_place)
ON DELETE CASCADE;

ALTER TABLE directory
ADD FOREIGN KEY(fk_userProj)
REFERENCES userProj (id_user)
ON DELETE CASCADE;

insert into UserType(title) values('admin') , ('normal');

insert into USERPROJ (USERNAME,PASSWORD,FK_USERTYPE) values ('cacique','202cb962ac59075b964b07152d234b70',1);
insert into USERINFO (EMAIL,FULLNAME,BIRTHDAY) values ('cacique@hotmail.com','Pedro Cacique','1987-03-18');

select * from UserProj inner join UserInfo 
on UserProj.ID_USER = UserInfo.ID_USERINFO
join UserType 
on UserProj.FK_USERTYPE= UserType.ID_USERTYPE;