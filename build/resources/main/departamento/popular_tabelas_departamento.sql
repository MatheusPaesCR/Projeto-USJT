## Inserindo dados nas tabelas

## Incluindo na tabela USUARIO
INSERT INTO USUARIO(nome_completo, nome_de_usuario, email, senha, telefone) VALUES ("Luiz Alex Umbelino","alex.umbelino","alex@gmail.com.br", "teste123", "999999999");
INSERT INTO USUARIO(nome_completo, nome_de_usuario, email, senha, telefone) VALUES ("Matheus Paes Crescêncio","matheuspc","matheus@gmail.com.br", 1234, 11912345678);
INSERT INTO USUARIO(nome_completo, nome_de_usuario, email, senha, telefone) VALUES ("Usuário de Teste","teste","teste@gmail.com.br", 0000, 11955552222);

## Incluindo na tabela PROJETO
INSERT INTO PROJETO(nome_do_projeto, descricao, idUsuario) VALUES ("Sensor de Luz", "Gerencia a iluminação do ambiente", 1);
INSERT INTO PROJETO(nome_do_projeto, descricao, idUsuario) VALUES ("Guindaste", "Carregava coisas com um imã", 1);
INSERT INTO PROJETO(nome_do_projeto, descricao, idUsuario) VALUES ("Maquina de pressão", "Pressionava as coisas", 2);
INSERT INTO PROJETO(nome_do_projeto, descricao, idUsuario) VALUES ("Batalha Naval","Jogo programado em C", 3);
INSERT INTO PROJETO(nome_do_projeto, descricao, idUsuario) VALUES ("Minha Casa Minha Vida","Projeto de habitação popular", 3);
INSERT INTO PROJETO(nome_do_projeto, descricao, idUsuario) VALUES ("Editor de Fotos","Programa que realiza a edição de fotos", 3);

## Incluindo na tabela REQUISITOS
INSERT INTO REQUISITO(nome, modulo, funcionalidades, data_de_criacao, data_da_ultima_alteracao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao, idProjeto, idAutor, idAutorUltimaModificacao) VALUES("Ascender a LED quando a luminosidade diminuir", 1, "Ascender a LED", "2021-10-05", "2021-10-09", 3, "URGENTE", "MUITO_ALTA", "18", "NAO_INICIADO", "ANALISE", "O sensor deve captar a luminosidade do ambiente e ascender a LED quando a luminosidade for menor que 15 lúmens", 1, 1, 2);
INSERT INTO REQUISITO(nome, modulo, funcionalidades, data_de_criacao, data_da_ultima_alteracao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao, idProjeto, idAutor, idAutorUltimaModificacao) VALUES("Mudar a cor da LED", 1, "Mudar a cor", "2021-10-06", "2021-10-10", 1, "MEDIA", "BAIXA", "6", "EM_DESENVOLVIMENTO", "DESENVOLVIMENTO", "O usuário deve ser capaz de alterar a cor emitida pela LED.", 1, 2, 3);
