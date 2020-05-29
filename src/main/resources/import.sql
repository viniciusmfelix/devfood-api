INSERT INTO cozinha (nome) VALUES ('Chinesa');
INSERT INTO cozinha (nome) VALUES ('Japonesa');
INSERT INTO cozinha (nome) VALUES ('Italiana');
INSERT INTO cozinha (nome) VALUES ('Árabe');
INSERT INTO cozinha (nome) VALUES ('Brasileira');
INSERT INTO cozinha (nome) VALUES ('Portuguesa');

INSERT INTO estado (nome) VALUES ('Bahia');
INSERT INTO estado (nome) VALUES ('Sergipe');
INSERT INTO estado (nome) VALUES ('São Paulo');
INSERT INTO estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO estado (nome) VALUES ('Rio Grande do Sul');
INSERT INTO estado (nome) VALUES ('Amazonas');
INSERT INTO estado (nome) VALUES ('Pará');
INSERT INTO estado (nome) VALUES ('Ceará');

INSERT INTO cidade (nome, estado_id) VALUES ('Salvador', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Aracaju', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('São Paulo', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Rio de Janeiro', 4);
INSERT INTO cidade (nome, estado_id) VALUES ('Porto Alegre', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('Belém', 6);
INSERT INTO cidade (nome, estado_id) VALUES ('Jericoacoara', 7);

INSERT INTO restaurante (data_atualizacao, data_cadastro, endereco_bairro,endereco_cep,endereco_complemento,endereco_logradouro,endereco_numero,nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (utc_timestamp, utc_timestamp, 'Jardim Sabará','04446-180','Bloco Beija Flor Apto 04A','Rua Curiá','540','Nobuken',13.99,2,2);
INSERT INTO restaurante (data_atualizacao, data_cadastro, endereco_bairro,endereco_cep,endereco_complemento,endereco_logradouro,endereco_numero,nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (utc_timestamp, utc_timestamp, 'Jardim Sabará','04446-180','Bloco Beija Flor Apto 04A','Rua Curiá','540','Porto Salgado',0,6,2);
INSERT INTO restaurante (data_atualizacao, data_cadastro, endereco_bairro,endereco_cep,endereco_complemento,endereco_logradouro,endereco_numero,nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (utc_timestamp, utc_timestamp, 'Jardim Sabará','04446-180','Bloco Beija Flor Apto 04A','Rua Curiá','540','Spoleto',5.99,3,2);
INSERT INTO restaurante (data_atualizacao, data_cadastro, endereco_bairro,endereco_cep,endereco_complemento,endereco_logradouro,endereco_numero,nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (utc_timestamp, utc_timestamp, 'Jardim Sabará','04446-180','Bloco Beija Flor Apto 04A','Rua Curiá','540','Feijuca da Tia',4.99,5,2);
INSERT INTO restaurante (data_atualizacao, data_cadastro, endereco_bairro,endereco_cep,endereco_complemento,endereco_logradouro,endereco_numero,nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (utc_timestamp, utc_timestamp, 'Jardim Sabará','04446-180','Bloco Beija Flor Apto 04A','Rua Curiá','540','China In Box',5.99,1,2);
INSERT INTO restaurante (data_atualizacao, data_cadastro, endereco_bairro,endereco_cep,endereco_complemento,endereco_logradouro,endereco_numero,nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (utc_timestamp, utc_timestamp, 'Jardim Sabará','04446-180','Bloco Beija Flor Apto 04A','Rua Curiá','540','Habib\'s',13.99,4,2);

INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id) VALUES (1,'Hot Roll delicioso!','Hot Roll', 24.99, 1);
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id) VALUES (1,'Delicioso Temaki de Salmão Grelhado com Molho Tarê!','Temaki Philadelphia', 34.99, 1);

INSERT INTO forma_pagamento (descricao) VALUES ('Crédito Master');
INSERT INTO forma_pagamento (descricao) VALUES ('Crédito Visa');
INSERT INTO forma_pagamento (descricao) VALUES ('Crédito American Express');
INSERT INTO forma_pagamento (descricao) VALUES ('Débito Master');
INSERT INTO forma_pagamento (descricao) VALUES ('Débito Visa');
INSERT INTO forma_pagamento (descricao) VALUES ('Débito American Express');
INSERT INTO forma_pagamento (descricao) VALUES ('Ticket Restaurante');
INSERT INTO forma_pagamento (descricao) VALUES ('Sodexo Refeição');
INSERT INTO forma_pagamento (descricao) VALUES ('Vale Refeição');

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1,1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1,2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1,4);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1,5);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1,7);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1,8);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1,9);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2,1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2,2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2,4);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2,5);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2,7);