create table user_profile 
(  user_id varchar(20) not null,
   passwd varchar(20) not null,
   primary key (user_id) 
);

create table role 
(	name varchar(20) not null,
   	description varchar(50) not null,
   
   	primary key (name)
);
   
create table user_role
(
	user_id varchar(20) not null,
	role_name varchar(20) not null,
	primary key (user_id, role_name),
	foreign key (user_id) references user_profile(user_id),
	foreign key (role_name) references role(name)
);


-- Seed Data
insert into user_profile values ('coolnri', 'coolnri');
insert into user_profile values ('testseeker', 'test');
insert into user_profile values ('testemployer', 'test');
insert into user_profile values ('testagency', 'test');

insert into role values ('admin', 'Administrator');
insert into role values ('jobseeker', 'Job Seeker');
insert into role values ('employer', 'Employer');
insert into role values ('agency', 'Employment Agency');

insert into user_role values ('coolnri', 'admin');
insert into user_role values ('testseeker', 'jobseeker');
insert into user_role values ('testemployer', 'employer');
insert into user_role values ('testagency', 'agency');







