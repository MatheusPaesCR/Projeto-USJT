## Criando database
CREATE DATABASE departamento;

## Escolhendo o database a ser usado
USE departamento;

##Criando tabelas
CREATE TABLE USUARIO
(
   nome_de_usuario VARCHAR(50) NOT NULL,
   nome_completo VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   senha VARCHAR(50)  NOT NULL,
   telefone VARCHAR(50) NOT NULL,
   PRIMARY KEY(nome_de_usuario)
);

CREATE TABLE PROJETO
(
  idProjeto INT NOT NULL AUTO_INCREMENT,
  nome_do_projeto TEXT,
  descricao TEXT,
  proprietario VARCHAR(50) NOT NULL,
  PRIMARY KEY (idProjeto),
  FOREIGN KEY (proprietario) REFERENCES USUARIO(nome_de_usuario)
);

CREATE TABLE REQUISITO
(
   idRequisito INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(50),
   modulo INT,
   funcionalidades TEXT NOT NULL,
   data_de_criacao DATE,
   data_da_ultima_alteracao DATE,
   versao INT,
   prioridade TEXT,
   complexidade TEXT,
   esforco_horas INT,
   estado TEXT,
   fase TEXT,
   descricao TEXT,
   idProjeto INT NOT NULL,
   autor VARCHAR(50) NOT NULL,
   autorUltimaModificacao VARCHAR(50) NOT NULL,
   PRIMARY KEY (idRequisito),
   FOREIGN KEY (idProjeto) REFERENCES PROJETO(idProjeto),
   FOREIGN KEY (autor) REFERENCES USUARIO(nome_de_usuario),
   FOREIGN KEY (autorUltimaModificacao) REFERENCES USUARIO(nome_de_usuario)
);