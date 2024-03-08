drop table if exists customer_financial_product;
drop table if exists financial_products;
drop table if exists transactions;
drop table if exists customers;
drop table if exists accounts;

drop sequence if exists account_number_seq;
create sequence account_number_seq start 1000;

create table accounts(
	id bigserial primary key not null,
	number integer not null default nextval('account_number_seq'),
	type smallint not null check (type = 0 or type = 1)
);

create table customers(
	id bigserial primary key not null,
	name varchar(255) not null,
	email varchar(255) unique not null,
	phone varchar(255) not null,
	account_id bigint references accounts
);

create table transactions(
    id bigserial primary key not null,
    type smallint not null check (type = 0 or type = 1),
    amount numeric(19,4) not null,
    date timestamp not null,
    account_id bigint not null references accounts
);

create table financial_products(
    id bigserial primary key not null,
    name varchar(255) not null
);

create table customer_financial_product(
    customer_id bigint not null references customers,
    financial_product_id bigint not null references financial_products,
    primary key (customer_id, financial_product_id)
);

insert into financial_products (name) values ('Investment');
insert into financial_products (name) values ('Credit Card');
insert into financial_products (name) values ('Loan');
