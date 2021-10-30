#criando database
CREATE DATABASE departamento;
#Escolhendo o database a ser usado
USE departamento;
#Criando tabelas

CREATE TABLE USUARIO
(
   nome_completo varchar(50) NOT NULL,
   nome_de_usuario varchar(50) NOT NULL,
   email varchar(50) NOT NULL,
   senha varchar(50)  NOT NULL,
   telefone varchar(50) NOT NULL,
   primary key(nome_de_usuario) 
);

CREATE TABLE PROJETO
( 
  registro_do_projeto int not null,	
  nome_do_projeto text,
  descricao text,
  usuario_proprietario character(50),
  PRIMARY KEY (registro_do_projeto),
  FOREIGN KEY (usuario_proprietario) REFERENCES USUARIO(nome_de_usuario)	
);

CREATE TABLE REQUISITOS
(
   identificador int not null,
   nome  VARCHAR(50),
   modulo int,
   funcionalidades text not null, 
   data_de_criacao DATE,
   autor varchar(50),
   data_da_ultima_alteracao DATE,
   autor_da_ultima_modificação VARCHAR(50) NOT NULL,
   versao  INT,
   prioridade TEXT,
   complexidade TEXT,
   esforco_horas TIME, ##esforço estimado em horas
   estado text, #,"Finalizado","Concluido","Circuito com sensor de luz,relé, placa arduino e resistores"
   fase text, ## inicial,em andamento, final, concluido
   descricao text,
   
   
   FOREIGN KEY (identificador) REFERENCES PROJETO(registro_do_projeto)
);


#inserindo dados nas tabelas

##incluindo na tabela usuario
INSERT INTO USUARIO VALUES ("Luiz Alex Umbelino","alex.umbelino","alex@gmail.com.br",1967,999999999);
INSERT INTO USUARIO VALUES ("aa aa","a.a","a@gmail.com.br",1234,888888888);
INSERT INTO USUARIO VALUES ("bb bb","b.b","b@gmail.com.br",5678,777777777);
INSERT INTO USUARIO VALUES ("cc cc","c.c","c@gmail.com.br",4321,666666666);
INSERT INTO USUARIO VALUES ("Atualizei","d.d","c@gmail.com.br",2021,55555555);
INSERT INTO USUARIO VALUES ("Atualizei222","d.d22","c2@gmail.com.br",2021,55555555);
INSERT INTO USUARIO VALUES ("Atualize333","d.d33","c2@gmail.com.br",2021,55555555);
##incluindo na tabela projeto
INSERT INTO PROJETO VALUES (1,"Sensor de Luz","Gerencia a iluminação do ambiente","alex.umbelino");
INSERT INTO PROJETO VALUES (2,"guidaste","carregava coisas com ima","alex.umbelino");
INSERT INTO PROJETO VALUES (3,"Maquina de pressão","apertava coisas","b.b");
INSERT INTO PROJETO VALUES (4,"Batalha naval","Jogo em C","c.c");
##incluindo na tabela requisitos
INSERT INTO REQUISITOS VALUES(1,"nome do requisito",1,"gerenciar a luz","2021-10-05","a.a","2021-11-21","a.a",1,"MEDIA","MEDIA","13:40:00","finalizado","concluido","Componentes eletronicos com placa arduino");
INSERT INTO REQUISITOS VALUES(2,"Tamanho",2,"abrigar conteudos","2021-11-10","a.a","2021-11-21","b.b",3,"MEDIA","MEDIA","13:40:00","finalizado","concluido","Componentes eletronicos com placa arduino");
INSERT INTO REQUISITOS VALUES(3,"cor",3,"gerenciar a luz","2021-10-05","a.a","2021-11-18","a.a",13,"baixa","MEDIA","13:40:00","finalizado","concluido","Componentes eletronicos com placa arduino");
INSERT INTO REQUISITOS VALUES(4,"Durabilidade",4,"gerenciar a luz","2020-05-05","a.a","2021-11-21","alex.umbelino",5,"alta","MEDIA","13:40:00","finalizado","concluido","Componentes eletronicos com placa arduino");

##mosrando conteudo da tabela 
SELECT * from PROJETO ;
SELECT * from USUARIO;
SELECT * from REQUISITOS;
SELECT nome_de_usuario,senha from USUARIO;

##deletar tabela:
drop table usuario;
##excluir o banco de dados:
DROP DATABASE departamento;