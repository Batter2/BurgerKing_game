create table memberInfo(
id varchar2(30) not null
constraint memberInfo_id_pk primary key,
password varchar2(30) not null,
nickname varchar2(30) not null);

drop table memberInfo;

create table Ranking(
nickname varchar2(30) not null,
score number(20) not null);

select nickname from memberInfo where id = 'chp'

select * from RANKING

delete MEMBERINFO
delete RANKING

select * from MEMBERINFO

select * from ranking where rownum < 101 order by score