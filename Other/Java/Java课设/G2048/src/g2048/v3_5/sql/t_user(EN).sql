create table t_user
(
    Tname      varchar(20)                 not null
        primary key,
    Trname     varchar(15)                 not null,
    Tpasswd    varchar(15)                 not null,
    Tcard      char(18)                    not null,
    Tsex       char                        not null,
    Tage       int(100)      default 18    not null,
    Ttime      varchar(30)                 not null,
    Tscore3    int           default 1     not null,
    Tjindu3    varchar(3333) default '333' null,
    Ttime3     varchar(30)                 null,
    Tscore4    int           default 1     not null,
    Tjindu4    varchar(4444) default '444' null,
    Ttime4     varchar(30)                 null,
    Tscore5    int           default 1     not null,
    Tjindu5    varchar(5555) default '555' null,
    Ttime5     varchar(30)                 null,
    Status     varchar(3)                  not null,
    StatusTime varchar(30)                 not null,
    constraint t_user_Tname_uindex
        unique (Tname)
);