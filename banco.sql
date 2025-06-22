/*
Created: 26/04/2025
Modified: 22/06/2025
Model: PostgreSQL 10
Database: PostgreSQL 10
*/


-- Create tables section -------------------------------------------------

-- Table Pessoa

CREATE TABLE Pessoa(
 Id_Pessoa Serial NOT NULL,
 Nome Character varying(100) NOT NULL,
 DDD_Telefone Smallint,
 Telefone Numeric,
 Nome_de_Usuario Character varying(25) NOT NULL,
 Senha Character varying(28) NOT NULL,
 Email Character varying(20) NOT NULL,
 Papel Smallint NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Pessoa

ALTER TABLE Pessoa ADD CONSTRAINT PK_Pessoa PRIMARY KEY (Id_Pessoa)
;

ALTER TABLE Pessoa ADD CONSTRAINT id UNIQUE (Id_Pessoa)
;

ALTER TABLE Pessoa ADD CONSTRAINT Email UNIQUE (Email)
;

ALTER TABLE Pessoa ADD CONSTRAINT Nome_de_Usuario UNIQUE (Nome_de_Usuario)
;

-- Table Aluno

CREATE TABLE Aluno(
 Id_Aluno Serial NOT NULL,
 Id_Pessoa Integer NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Aluno

ALTER TABLE Aluno ADD CONSTRAINT PK_Aluno PRIMARY KEY (Id_Aluno,Id_Pessoa)
;

ALTER TABLE Aluno ADD CONSTRAINT Id_Aluno UNIQUE (Id_Aluno)
;

-- Table Professor

CREATE TABLE Professor(
 Id_Professor Serial NOT NULL,
 Id_Pessoa Integer NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Professor

ALTER TABLE Professor ADD CONSTRAINT PK_Professor PRIMARY KEY (Id_Professor,Id_Pessoa)
;

ALTER TABLE Professor ADD CONSTRAINT Id_Professor UNIQUE (Id_Professor)
;

-- Table Cadeira

CREATE TABLE Cadeira(
 Id_Cadeira Serial NOT NULL,
 Nome Character varying(40) NOT NULL,
 Codigo Character varying(20) NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Cadeira

ALTER TABLE Cadeira ADD CONSTRAINT PK_Cadeira PRIMARY KEY (Id_Cadeira)
;

ALTER TABLE Cadeira ADD CONSTRAINT Id_Cadeira UNIQUE (Id_Cadeira)
;

ALTER TABLE Cadeira ADD CONSTRAINT Nome UNIQUE (Nome)
;

ALTER TABLE Cadeira ADD CONSTRAINT Codigo UNIQUE (Codigo)
;

-- Table Turma

CREATE TABLE Turma(
 Id_Turma Serial NOT NULL,
 Semestre Character varying(9) NOT NULL,
 Vagas_Disponibilizadas Integer,
 Vagas_Ocupadas Integer,
 Dias Character varying(40),
 Horario Time,
 Id_Sala Integer NOT NULL,
 Id_Cadeira Integer NOT NULL,
 Id_Professor Integer
)
WITH (
 autovacuum_enabled=true)
;

-- Create indexes for table Turma

CREATE INDEX IX_Relationship33 ON Turma (Id_Professor)
;

-- Add keys for table Turma

ALTER TABLE Turma ADD CONSTRAINT PK_Turma PRIMARY KEY (Id_Turma,Id_Sala,Id_Cadeira)
;

ALTER TABLE Turma ADD CONSTRAINT Id_Turma UNIQUE (Id_Turma)
;

-- Table Sala

CREATE TABLE Sala(
 Id_Sala Serial NOT NULL,
 Capacidade Integer NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Sala

ALTER TABLE Sala ADD CONSTRAINT PK_Sala PRIMARY KEY (Id_Sala)
;

ALTER TABLE Sala ADD CONSTRAINT Id_Sala UNIQUE (Id_Sala)
;

-- Table Administrador

CREATE TABLE Administrador(
 Id_Administrador Serial NOT NULL,
 Id_Pessoa Integer NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Administrador

ALTER TABLE Administrador ADD CONSTRAINT PK_Administrador PRIMARY KEY (Id_Administrador,Id_Pessoa)
;

ALTER TABLE Administrador ADD CONSTRAINT Id_Admin UNIQUE (Id_Administrador)
;

-- Table Matricula

CREATE TABLE Matricula(
 Id_Aluno Integer NOT NULL,
 Id_Turma Integer NOT NULL,
 Data_Matricula Date,
 Data_CancelamentoMatricula Date,
 Data_EncerramentoMatricula Date,
 Id_Matricula Serial NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Matricula

ALTER TABLE Matricula ADD CONSTRAINT PK_Matricula PRIMARY KEY (Id_Aluno,Id_Turma,Id_Matricula)
;

ALTER TABLE Matricula ADD CONSTRAINT Id_Matricula UNIQUE (Id_Matricula)
;

-- Table Matricula_Pendente

CREATE TABLE Matricula_Pendente(
 Data_Matricula Date,
 Data_CancelamentoMatricula Date,
 Data_EncerramentoMatricula Date,
 Status Character varying(5),
 Id_Aluno Integer NOT NULL,
 Id_Turma Integer NOT NULL,
 Id_Matricula_Pendente Serial NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Matricula_Pendente

ALTER TABLE Matricula_Pendente ADD CONSTRAINT PK_Matricula_Pendente PRIMARY KEY (Id_Aluno,Id_Turma,Id_Matricula_Pendente)
;

ALTER TABLE Matricula_Pendente ADD CONSTRAINT Id_Matricula_Pendente UNIQUE (Id_Matricula_Pendente)
;

-- Table Mensagens

CREATE TABLE Mensagens(
 Id_Mensagem Serial NOT NULL,
 Tipo_Mensagem Character(1) NOT NULL,
 Mensagem Character varying(60) NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Mensagens

ALTER TABLE Mensagens ADD CONSTRAINT PK_Mensagens PRIMARY KEY (Id_Mensagem)
;

ALTER TABLE Mensagens ADD CONSTRAINT Id_Mensagem UNIQUE (Id_Mensagem)
;

-- Table Presenca

CREATE TABLE Presenca(
 Id_Aluno Integer NOT NULL,
 Id_Turma Integer NOT NULL,
 Data Time NOT NULL,
 Presente Boolean NOT NULL
)
WITH (
 autovacuum_enabled=true)
;

-- Add keys for table Presenca

ALTER TABLE Presenca ADD CONSTRAINT PK_Presenca PRIMARY KEY (Id_Aluno,Id_Turma)
;

-- Table Notificacao

CREATE TABLE Notificacao(
 Id_Professor Integer NOT NULL,
 Status Character varying(5),
 Id_Matricula_Pendente Integer NOT NULL
)
WITH (
 autovacuum_enabled=true)
;
COMMENT ON COLUMN Notificacao.Status IS 'Estatus da matricula do aluno Aberta(NULL), Processada(PROC), Aceita pelo professor(ACE), Rejeitada pelo professor(REJ)'
;

-- Add keys for table Notificacao

ALTER TABLE Notificacao ADD CONSTRAINT PK_Notificacao PRIMARY KEY (Id_Professor,Id_Matricula_Pendente)
;
-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE Aluno ADD CONSTRAINT Relationship4 FOREIGN KEY (Id_Pessoa) REFERENCES Pessoa (Id_Pessoa) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Professor ADD CONSTRAINT Relationship5 FOREIGN KEY (Id_Pessoa) REFERENCES Pessoa (Id_Pessoa) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Administrador ADD CONSTRAINT Relationship6 FOREIGN KEY (Id_Pessoa) REFERENCES Pessoa (Id_Pessoa) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Turma ADD CONSTRAINT Relationship8 FOREIGN KEY (Id_Sala) REFERENCES Sala (Id_Sala) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Turma ADD CONSTRAINT Relationship9 FOREIGN KEY (Id_Cadeira) REFERENCES Cadeira (Id_Cadeira) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Matricula ADD CONSTRAINT Relationship14 FOREIGN KEY (Id_Turma) REFERENCES Turma (Id_Turma) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Matricula ADD CONSTRAINT Relationship15 FOREIGN KEY (Id_Aluno) REFERENCES Aluno (Id_Aluno) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Presenca ADD CONSTRAINT Relationship23 FOREIGN KEY (Id_Aluno) REFERENCES Aluno (Id_Aluno) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Presenca ADD CONSTRAINT Relationship26 FOREIGN KEY (Id_Turma) REFERENCES Turma (Id_Turma) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Notificacao ADD CONSTRAINT Relationship30 FOREIGN KEY (Id_Professor) REFERENCES Professor (Id_Professor) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Turma ADD CONSTRAINT Relationship33 FOREIGN KEY (Id_Professor) REFERENCES Professor (Id_Professor) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Matricula_Pendente ADD CONSTRAINT Relationship34 FOREIGN KEY (Id_Aluno) REFERENCES Aluno (Id_Aluno) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Matricula_Pendente ADD CONSTRAINT Relationship35 FOREIGN KEY (Id_Turma) REFERENCES Turma (Id_Turma) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Notificacao ADD CONSTRAINT Relationship36 FOREIGN KEY (Id_Matricula_Pendente) REFERENCES Matricula_Pendente (Id_Matricula_Pendente) ON DELETE NO ACTION ON UPDATE NO ACTION
;


ALTER SEQUENCE Administrador_id_Administrador_seq RESTART WITH 1;
ALTER SEQUENCE Aluno_Id_Aluno_seq RESTART WITH 1;
ALTER SEQUENCE Cadeira_Id_Cadeira_seq RESTART WITH 1;
ALTER SEQUENCE Mensagens_Id_Mensagem_seq RESTART WITH 1;
ALTER SEQUENCE Pessoa_Id_Pessoa_seq RESTART WITH 1;
ALTER SEQUENCE Matricula_Id_Matricula_seq RESTART WITH 1;
ALTER SEQUENCE Professor_Id_Professor_seq RESTART WITH 1;
ALTER SEQUENCE Sala_Id_Sala_seq RESTART WITH 1;
ALTER SEQUENCE Turma_Id_Turma_seq RESTART WITH 1;

-- Valores para teste do banco

INSERT INTO pessoa
(id_pessoa, nome, ddd_telefone, telefone, nome_de_usuario, senha, email, papel)
VALUES(nextval('pessoa_id_pessoa_seq'), 'Administrador do sistema', 88 , 35032469, 'admin', 'admin', 'admin@gmail.com', 0);

INSERT INTO administrador
(id_administrador, id_pessoa)
VALUES(nextval('administrador_id_administrador_seq'), (select id_pessoa from pessoa where nome_de_usuario = 'admin'));

INSERT INTO pessoa
(id_pessoa, nome, ddd_telefone, telefone, nome_de_usuario, senha, email, papel)
VALUES(nextval('pessoa_id_pessoa_seq'), 'Primeiro Aluno', 62 , 27568802, 'aluno', 'aluno', 'aluno@gmail.com', 1);

INSERT INTO aluno
(id_aluno, id_pessoa)
VALUES(nextval('aluno_id_aluno_seq'), (select id_pessoa from pessoa where nome_de_usuario = 'aluno'));

INSERT INTO pessoa
(id_pessoa, nome, ddd_telefone, telefone, nome_de_usuario, senha, email, papel)
VALUES(nextval('pessoa_id_pessoa_seq'), 'Primeiro Professor', 79 , 28434726, 'professor', 'professor', 'professor@gmail.com', 2);

INSERT INTO professor
(id_professor, id_pessoa)
VALUES(nextval('professor_id_professor_seq'), (select id_pessoa from pessoa where nome_de_usuario = 'professor'));


INSERT INTO cadeira
(id_cadeira, nome, codigo)
VALUES(nextval('cadeira_id_cadeira_seq'), 'Engenharia de Software', 'Eng01');

INSERT INTO cadeira
(id_cadeira, nome, codigo)
VALUES(nextval('cadeira_id_cadeira_seq'), 'Calculo1', 'Calc01');

