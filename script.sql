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

create table city (
	id integer primary key auto_increment,
	city_name varchar(30) not null
);

insert into city values('Guingamp');
insert into city values('Lille');
insert into city values('Arras');

create table hotel (
	id integer primary key auto_increment,
	id_city integer not null,
	name varchar(30) not null,
	constraint fk_city foreign key (id_city) references city(id)
	on delete cascade
	on update cascade 
);

create table category (
	id integer primary key auto_increment,
	id_hotel integer not null,
	capacity integer not null,
	price float(6,2) not null,
	constraint fk_hotel foreign key (id_hotel) references hotel(id)
	on delete cascade 
	on update cascade
);


alter table category add name_category varchar(30) not null;

alter table room add name_room varchar(30) not null;

create table room (
	id_hotel integer not null,
	id_category integer not null,
	room_number integer not null,
	constraint pk_room primary key (id_hotel, id_category), 
	constraint fk_hotel_room foreign key (id_hotel) references hotel(id),
	constraint fk_category_room foreign key (id_category) references category(id)
);

insert into category (id_hotel, capacity, price, name_category) values (
	3,
	2,
	15.50,
	'Normal'
);

insert into category (id_hotel, capacity, price, name_category) values (
	3,
	1,
	7.30,
	'Cheap'
);

insert into category (id_hotel, capacity, price, name_category) values (
	3,
	4,
	70.90,
	'Deluxe'
);

insert into room (id_hotel, id_category, room_number, name_room) values (
	3, 
	1,
	12,
	'Alexandria'
);