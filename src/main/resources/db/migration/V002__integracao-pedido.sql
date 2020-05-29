create table pedido (
	id bigint not null auto_increment,
    subtotal decimal(19,2) not null,
    taxa_frete decimal(19,2) not null,
    valor_total decimal(19,2) not null,
    endereco_bairro varchar(50), 
    endereco_cep varchar(20), 
    endereco_complemento varchar(120), 
    endereco_logradouro varchar(120), 
    endereco_numero varchar(5),
    status_pedido varchar(20) not null,
    data_criacao datetime not null,
    data_confirmacao datetime,
    data_cancelamento datetime,
    data_entrega datetime,
    restaurante_id bigint not null,
    cliente_id bigint not null,
    forma_pagamento_id bigint,
    primary key(id)
) engine=InnoDB default charset=utf8mb4;

alter table pedido add constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id);
alter table pedido add constraint fk_pedido_cliente foreign key (cliente_id) references usuario (id);
alter table pedido add constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);

create table item_pedido (
	id bigint not null auto_increment,
    quantidade int not null,
    preco_unitario decimal(19,2) not null,
    preco_total decimal(19,2) not null,
    observacao varchar(120) not null,
    pedido_id bigint not null,
    produto_id bigint not null,
    primary key(id)
) engine=InnoDB default charset=utf8mb4;

alter table item_pedido add constraint fk_item_pedido_id foreign key (pedido_id) references pedido (id);
alter table item_pedido add constraint fk_item_pedido_produto foreign key (produto_id) references produto (id);
