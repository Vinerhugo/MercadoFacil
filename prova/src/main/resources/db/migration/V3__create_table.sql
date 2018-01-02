create table usuario (
  id int NOT NULL IDENTITY,
  nomeuser VARCHAR(256) NOT NULL,
  senha int not null,
  adm boolean,
  PRIMARY KEY (id));
  
  create table produto(
  id int NOT NULL IDENTITY,
  nomeprod VARCHAR(256) NOT NULL,
  codbarra int not null,
  preco double,
  PRIMARY KEY (id));
  
 create table carrinho(
  id int NOT NULL IDENTITY,
  nomecarrinho VARCHAR(256) NOT NULL,
  datacarrinho timestamp,
  usuario_id int not null,
  foreign key(usuario_id) references usuario(id),
  PRIMARY KEY (id));