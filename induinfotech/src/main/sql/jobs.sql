// Drop
drop table employee_skill;
drop table skill;
drop table employee;

// Create
create table employee 
(
	id int(10) not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	middle_initial char(5),
	email varchar(30) not null,
	phone_nbr varchar(20) not null,
	status char(1) not null,
	
	start_date date,
	end_data date,
	
	employer_id varchar(20) not null,
	
	primary key (id),
	foreign key (employer_id) references user_profile(user_id)
);

create table skill
(
	name varchar(30) not null,	
	primary key (name)
);

create table employee_skill
(
	employee_id int(10) not null,
	skill       varchar(30) not null,
	years       int(2) not null,
	
	primary key (employee_id, skill),
	
	foreign key (employee_id) references employee(id),
	foreign key (skill) references skill(name)
);