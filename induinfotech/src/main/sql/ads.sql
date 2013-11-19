-- Drop tables
drop table adg_keyword ;
drop table creative;
drop table keyword;
drop table adgroup;
drop table campaign;

-- Recreate tables
create table campaign
( 
	campaign_id integer not null AUTO_INCREMENT,
	name varchar(50) not null,
	start_dt date not null,
	end_dt date not null,
	daily_budget decimal(10,2) not null,
	daily_budget_currency varchar(5) not null,
	primary key (campaign_id)
);

create table adgroup
(
	campaign_id integer not null,
	adgroup_id integer not null,
	name varchar(50) not null,
	daily_budget decimal(10,2) not null,
	daily_budget_currency varchar(5) not null,
	primary key (campaign_id, adgroup_id),
	foreign key (campaign_id) references campaign(campaign_id)
);

create table keyword
(
	keyword_id integer not null AUTO_INCREMENT,
	keyword varchar(50) not null,
	active_flag char(1) not null,
	primary key (keyword_id)
);

create table creative
(
	campaign_id integer not null,
	adgroup_id integer not null,
	
	creative_id integer not null,
	
	headline varchar(50) not null,
	line1 varchar(100) not null,
	line2 varchar(100),
	image_url varchar(100),
	clickthru_url varchar(100),
	per_impression_price decimal(5,2), 
	per_click_price decimal(5,2),
	
	primary key(campaign_id, adgroup_id, creative_id),
	foreign key(campaign_id, adgroup_id) references adgroup(campaign_id, adgroup_id)
);

create table adg_keyword
(
    campaign_id integer not null,
	adgroup_id integer not null,
	keyword_id integer not null,
	primary key(campaign_id, adgroup_id, keyword_id),
	foreign key (campaign_id, adgroup_id) references adgroup(campaign_id, adgroup_id),
	foreign key (keyword_id) references keyword(keyword_id)
);
