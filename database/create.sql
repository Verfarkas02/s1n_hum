create database hum
character set utf8
collate utf8_hungarian_ci;

use hum;

create table employees (
    id int not null primary key auto_increment,
    name varchar(50),
    city varchar(50),
    salary double
);

grant all privileges
on hum.*
to hum@localhost
identified by 'titok';
