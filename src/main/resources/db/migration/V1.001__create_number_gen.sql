create table sl_number_gen
(
	id bigint auto_increment primary key,
	`version` bigint not null,
	type varchar(16) not null,
	current_number bigint not null
);

CREATE UNIQUE INDEX unq_type ON sl_number_gen (type);