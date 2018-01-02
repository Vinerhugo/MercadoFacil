alter table email add column assunto varchar(256);

create table promocao(
id int NOT NULL IDENTITY,
periodopromocao varchar(256),
nomepromocao varchar (256),
caminhopromocao varchar(256),
produto_id int,
foreign key(produto_id) references produto(id),
PRIMARY KEY (id));

create table folheto(
id int not null IDENTITY,
periodofolheto varchar(256),
caminhofolheto varchar(256),
primary key(id));
