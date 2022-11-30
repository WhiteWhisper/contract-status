-- liquibase formatted sql

-- changeset liquibase:1
create table if not exists Codes (
  id identity PRIMARY KEY,
  code int not null unique
);

create table if not exists Transactions (
  code int not null,
  status varchar(10) not null,
  time_i timestamp not null,
  contract_number int not null
);

alter table Transactions add foreign key (code) references Codes(code);