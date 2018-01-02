create table email(
  id int NOT NULL IDENTITY,
  nome VARCHAR(256) NOT NULL,
  telefone int ,
  email varchar(256) not null,
  message varchar(256) not null,
  PRIMARY KEY (id));