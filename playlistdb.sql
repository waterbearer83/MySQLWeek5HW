create database if not exists playList;

use playList;

drop table if exists songs;

create table songs (
	id int(10) not null auto_increment primary key,
	name varchar(40) unique not null,
	artist varchar(40)
	);
	