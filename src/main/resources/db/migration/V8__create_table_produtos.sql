CREATE TABLE produtos (
   id BIGINT NOT NULL AUTO_INCREMENT,
   dtype VARCHAR(31),

   nome VARCHAR(75) NOT NULL,
   sku VARCHAR(75) NOT NULL UNIQUE,
   preco DECIMAL(10,4) NOT NULL,

   calibre_id BIGINT,

   PRIMARY KEY (id),

   CONSTRAINT fk_calibre_produto 
      FOREIGN KEY (calibre_id) 
      REFERENCES calibres(id)
);