create table cidade (
	id bigint not null auto_increment, 
    nome varchar(120) not null, 
    estado_id bigint not null, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table cozinha (
	id bigint not null auto_increment, 
    nome varchar(60) not null, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table estado (
	id bigint not null auto_increment, 
    nome varchar(60) not null, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table forma_pagamento (
	id bigint not null auto_increment, 
    descricao varchar(80) not null, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo (
	id bigint not null auto_increment, 
    nome varchar(80) not null, primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo_permissao (
	grupo_id bigint not null, 
    permissao_id bigint not null
) engine=InnoDB default charset=utf8mb4;

create table permissao (
	id bigint not null auto_increment, 
    descricao varchar(120) not null, 
    nome varchar(80) not null, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table produto (
	id bigint not null auto_increment, 
    ativo bit not null, 
    descricao varchar(120) not null, 
    nome varchar(80) not null, 
    preco decimal(19,2) not null, 
    restaurante_id bigint not null, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurante (
	id bigint not null auto_increment, 
    data_atualizacao datetime not null, 
    data_cadastro datetime not null, 
    endereco_bairro varchar(50), 
    endereco_cep varchar(20), 
    endereco_complemento varchar(120), 
    endereco_logradouro varchar(120), 
    endereco_numero varchar(5), 
    nome varchar(80) not null, 
    taxa_frete decimal(19,2) not null, 
    cozinha_id bigint not null, 
    endereco_cidade_id bigint, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurante_forma_pagamento (
	restaurante_id bigint not null, 
    forma_pagamento_id bigint not null
) engine=InnoDB default charset=utf8mb4;

create table usuario (
	id bigint not null auto_increment, 
    data_cadastro datetime(6) not null, 
    email varchar(80) not null, 
    nome varchar(80) not null, 
    senha varchar(80) not null, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table usuario_grupo (
	usuario_id bigint not null, 
    grupo_id bigint not null
) engine=InnoDB default charset=utf8mb4;

alter table cidade add constraint fk_cidade_estado foreign key (estado_id) references estado (id);
alter table grupo_permissao add constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao (id);
alter table grupo_permissao add constraint fk_grupo_permissao_grupo foreign key (grupo_id) references grupo (id);
alter table produto add constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante (id);
alter table restaurante add constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id);
alter table restaurante add constraint fk_restaurante_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table restaurante_forma_pagamento add constraint fk_restaurante_forma_pgto_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table restaurante_forma_pagamento add constraint fk_restaurante_forma_pgto_restaurante foreign key (restaurante_id) references restaurante (id);
alter table usuario_grupo add constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo (id);
alter table usuario_grupo add constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario (id);