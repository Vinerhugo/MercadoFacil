create table carrinho_produto(
carrinho_id int not null,
produto_id int not null,
foreign key(carrinho_id) references carrinho(id),
foreign key(produto_id) references produto(id),
primary key(carrinho_id, produto_id)
);