CREATE TABLE alunos (
   id BIGINT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(150) NOT NULL,
   cpf VARCHAR(11) NOT NULL UNIQUE,
   rg VARCHAR(12) NOT NULL UNIQUE,
   rg_data_expedicao DATE NOT NULL,

   telefone_principal VARCHAR(15) NOT NULL UNIQUE,
   telefone_principal_whatsapp TINYINT NOT NULL DEFAULT 0,
   telefone_secundario VARCHAR(15),
   telefone_secundario_whatsapp TINYINT,

   email_principal VARCHAR(255) NOT NULL UNIQUE,
   email_principal_validado TINYINT NOT NULL DEFAULT 0,
   email_secundario VARCHAR(255) UNIQUE,
   email_secundario_validado TINYINT NOT NULL DEFAULT 0,

   cep VARCHAR(12) NOT NULL,
   estado VARCHAR(25) NOT NULL,
   cidade VARCHAR(70) NOT NULL,
   logradouro VARCHAR(255) NOT NULL,
   numero_logradouro INT NOT NULL,
   complemento VARCHAR(200),

   PRIMARY KEY (id)
);