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

## Incluindo na tabela REQUISITOS
INSERT INTO REQUISITOS(nome, modulo, funcionalidades, data_de_criacao, autor, data_da_ultima_alteracao, autor_da_ultima_modificacao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao) VALUES("nome do requisito",1,"gerenciar a luz","2021-10-05","a.a","2021-11-21","a.a",1,"MEDIA","MEDIA","13:40:00","finalizado","concluido","Componentes eletronicos com placa arduino");
INSERT INTO REQUISITOS(nome, modulo, funcionalidades, data_de_criacao, autor, data_da_ultima_alteracao, autor_da_ultima_modificacao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao) VALUES("Tamanho",2,"abrigar conteudos","2021-11-10","a.a","2021-11-21","b.b",3,"MEDIA","MEDIA","13:40:00","finalizado","concluido","Componentes eletronicos com placa arduino");
INSERT INTO REQUISITOS(nome, modulo, funcionalidades, data_de_criacao, autor, data_da_ultima_alteracao, autor_da_ultima_modificacao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao) VALUES("cor",3,"gerenciar a luz","2021-10-05","a.a","2021-11-18","a.a",13,"baixa","MEDIA","13:40:00","finalizado","concluido","Componentes eletronicos com placa arduino");
INSERT INTO REQUISITOS(nome, modulo, funcionalidades, data_de_criacao, autor, data_da_ultima_alteracao, autor_da_ultima_modificacao, versao, prioridade, complexidade, esforco_horas, estado, fase, descricao) VALUES("Durabilidade",4,"gerenciar a luz","2020-05-05","a.a","2021-11-21","alex.umbelino",5,"alta","MEDIA","13:40:00","finalizado","concluido","Componentes eletronicos com placa arduino");
