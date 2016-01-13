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

create table room (
	id_hotel integer not null,
	id_category integer not null,
	room_number integer not null,
	constraint pk_room primary key (id_hotel, id_category), 
	constraint fk_hotel_room foreign key (id_hotel) references hotel(id),
	constraint fk_category_room foreign key (id_category) references category(id)
);

alter table room add name_room varchar(30) not null;
alter table room drop primary key, add primary key(id_hotel, id_category, room_number);

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

create table line (
	id_line integer not null,
	id_departure_city integer not null,
	id_arrival_city integer not null,
	constraint pk_line primary key (id_line),
	constraint fk_departure_city foreign key (id_departure_city) references city(id),
	constraint fk_arrival_city foreign key (id_arrival_city) references city(id)
);

alter table line modify column id_line integer not null auto_increment;
alter table line drop foreign key fk_departure_city;
alter table line drop foreign key fk_arrival_city;
alter table line add constraint fk_departure_city foreign key (id_departure_city) references city(id) on delete cascade;
alter table line add constraint fk_arrival_city foreign key (id_arrival_city) references city(id) on delete cascade;

create table flight (
	id_flight integer not null,
	id_line integer not null,
	day_of_week varchar(15) not null,
	departure_time varchar(6) not null,
	flight_duration integer not null,
	max_passenger_first_class integer not null,
	price_first_class float(6,2) not null,
	max_passenger_second_class integer not null,
	price_second_class float(6,2) not null,
	cancellation_time integer not null,
	constraint pk_flight primary key (id_flight),
	constraint fk_line foreign key (id_line) references line(id_line)
);

alter table flight drop foreign key fk_line;
alter table flight add constraint fk_line foreign key (id_line) references line(id_line) on delete cascade;
alter table flight modify column id_flight integer not null auto_increment;


insert into line (id_departure_city, id_arrival_city) values (
	2,
	3
);

insert into flight (id_line, day_of_week, departure_time, flight_duration, max_passenger_first_class,
					price_first_class, max_passenger_second_class, price_second_class, cancellation_time) values (

					1,
					'Lundi',
					'13h30',
					2,
					76,
					79.80,
					127,
					39.40,
					2
);

insert into flight (id_line, day_of_week, departure_time, flight_duration, max_passenger_first_class,
					price_first_class, max_passenger_second_class, price_second_class, cancellation_time) values (
					5,
					'Mercredi',
					'8h20',
					3,
					32,
					45.80,
					83,
					19.20,
					2
);

insert into flight (id_line, day_of_week, departure_time, flight_duration, max_passenger_first_class,
					price_first_class, max_passenger_second_class, price_second_class, cancellation_time) values (
					7,
					'Vendredi',
					'19h10',
					5,
					12,
					120.80,
					32,
					76.60,
					2
);

create table booking (
	id_booking integer not null primary key auto_increment,
	id_client integer not null,
	id_flight integer not null,
    id_category integer not null,
    id_hotel integer not null,
    room_number integer not null,
    id_city_departure integer not null,
    id_city_arrival integer not null,
    date_departure date not null,
    date_return date not null,
    nb_passager integer not null,
    price float(7,2) not null
);

alter table booking add constraint fk_customer foreign key (id_client) references customer(id);
alter table booking add constraint fk_flight foreign key (id_flight) references flight(id_flight);
alter table booking add constraint fk_room foreign key (id_hotel, id_category, room_number) references room(id_hotel, id_category, room_number);
alter table booking add constraint fk_city_departure foreign key (id_city_departure) references city(id);
alter table booking add constraint fk_city_arrival foreign key (id_city_arrival) references city(id);

alter table booking modify id_flight integer;
alter table booking modify id_category integer;
alter table booking modify id_hotel integer;
alter table booking modify room_number integer;
alter table booking modify price float(7,2);

insert into booking (id_client, id_city_departure, id_city_arrival, date_departure, date_return, nb_passager) values (
	1,
	2,
	8,
	'2016-01-13',
	'2016-01-17',
	2
);

