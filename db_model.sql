drop schema if exists warehouse;

create schema if not exists warehouse;

use warehouse;

create table if not exists client(
	id integer not null primary key auto_increment,
	firstName varchar(50),
    lastName varchar(50),
    dob date,
    email varchar(100),
    phoneNumber char(11)
);
create table if not exists product(
	id integer not null primary key auto_increment,
    productName varchar(50),
    price double,
    stock integer
);
create table if not exists orderC(
	id integer not null primary key auto_increment,
    productId integer not null,
    clientId integer not null,
    quantity integer not null,
    foreign key (productId) references product(id) on update cascade on delete cascade,
    foreign key (clientId) references client(id) on update cascade on delete cascade
);

create table if not exists log(
	id integer not null primary key auto_increment,
    productId integer not null,
    price integer not null,
    foreign key (productId) references product(id) on update cascade on delete cascade
);