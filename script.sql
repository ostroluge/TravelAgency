create table customer (
	id integer primary key auto_increment,
	last_name varchar(30) not null,
	first_name varchar(30) not null,
	birthdate date not null,
	origin_city varchar(30) not null
);

insert into customer (last_name, first_name, birthdate, origin_city) values (
	'FLAMEN',
	'Julien',
	'1994-01-23',
	'Lille'
);

insert into customer (last_name, first_name, birthdate, origin_city) values (
	'OSTROWSKI',
	'Thomas',
	'1994-01-23',
	'Arras'
);