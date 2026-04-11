
ALTER TABLE produtos 
ADD COLUMN quantidade INT NOT NULL DEFAULT 0,
ADD CONSTRAINT chk_quantidade_positiva CHECK (quantidade >= 0);

CREATE TABLE operacao (
   id BIGINT NOT NULL AUTO_INCREMENT,
   operacao ENUM ("CURSO", "INSTRUCAO", "TREINAMENTO", "DEVOLUCAO", "AJUSTE_MANUAL") NOT NULL,
   descricao VARCHAR(200) NOT NULL,
   tipo_operacao ENUM("entrada", "saida") NOT NULL,
   quantidade_movimentada INT NOT NULL,
   produto_id BIGINT NOT NULL,
   data_operacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   
   PRIMARY KEY (id),
   CONSTRAINT fk_operacao_produto_id FOREIGN KEY (produto_id) REFERENCES produtos(id)
);