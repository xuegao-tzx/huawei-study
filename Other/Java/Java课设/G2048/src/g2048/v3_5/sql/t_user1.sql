create table t_user1
(
    Tname    varchar(20)                  not null
        primary key,
    Tshare31 varchar(3333) default '3331' null,
    Tshare32 varchar(3)    default '111'  null,
    Tname3   varchar(20)                  null,
    Ttime3   varchar(30)                  null,
    Tshare41 varchar(4444) default '4441' null,
    Tshare42 varchar(3)    default '111'  null,
    Tname4   varchar(20)                  null,
    Ttime4   varchar(30)                  null,
    Tshare51 varchar(5555) default '5551' null,
    Tshare52 varchar(3)    default '111'  null,
    Tname5   varchar(20)                  null,
    Ttime5   varchar(30)                  null,
    constraint t_user_Tname_uindex
        unique (Tname)
);