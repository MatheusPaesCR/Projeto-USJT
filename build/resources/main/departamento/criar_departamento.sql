## Criando database
CREATE DATABASE departamento;

## Escolhendo o database a ser usado
USE departamento;

##Criando tabelas
CREATE TABLE USUARIO
(
   idUsuario INT NOT NULL AUTO_INCREMENT,
   nome_completo VARCHAR(50) NOT NULL,
   nome_de_usuario VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   senha VARCHAR(50)  NOT NULL,
   telefone VARCHAR(50) NOT NULL,
   PRIMARY KEY(idUsuario)
);

CREATE TABLE PROJETO
(
  idProjeto INT NOT NULL AUTO_INCREMENT,
  nome_do_projeto TEXT,
  descricao TEXT,
  idUsuario INT NOT NULL,
  PRIMARY KEY (idProjeto),
  FOREIGN KEY (idUsuario) REFERENCES USUARIO(idUsuario)
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
   esforco_horas TIME, ##esforço estimado em horas
   estado TEXT, #,"Finalizado","Concluido","Circuito com sensor de luz,relé, placa arduino e resistores"
   fase TEXT, ## inicial,em andamento, final, concluido
   descricao TEXT,
   idProjeto INT NOT NULL,
   idAutor INT NOT NULL,
   idAutorUltimaModificacao INT NOT NULL,
   PRIMARY KEY (idRequisito),
   FOREIGN KEY (idProjeto) REFERENCES PROJETO(idProjeto),
   FOREIGN KEY (idAutor) REFERENCES USUARIO(idUsuario),
   FOREIGN KEY (idAutorUltimaModificacao) REFERENCES USUARIO(idUsuario)
);