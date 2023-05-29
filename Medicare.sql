use medicare;

select *from customer;

select *from admin;

create table product(pid int auto_increment not null, pname varchar(100) not null, pcat varchar(100) not null, 
pdesc varchar(10000) not null, price int not null, labelCode varchar(100) not null, primary key(pid))

select *from product;

create table product_resource(resourceCode varchar(100) not null, resourceDetail varchar(100) not null);


select *from product_resource;

insert into product_resource(resourceCode, resourceDetail) values ('categories', 'Tablet');
insert into product_resource(resourceCode, resourceDetail) values ('categories', 'Ointment');
insert into product_resource(resourceCode, resourceDetail) values ('categories', 'Syrup');
insert into product_resource(resourceCode, resourceDetail) values ('categories', 'Injection');

drop table product;
drop table product_resource;