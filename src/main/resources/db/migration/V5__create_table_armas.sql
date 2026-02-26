CREATE table armas(
   id BIGINT NOT NULL AUTO_INCREMENT,
   local_registro_arma varchar(25) NOT NULL,
   numero_craf varchar(50) NOT NULL UNIQUE,
   numero_serie varchar(50) NOT NULL UNIQUE,
   numero_cano varchar(50) NOT NULL UNIQUE,
   modelo varchar(100) NOT NULL,
   calibre_id BIGINT NOT NULL,
   marca_id BIGINT NOT NULL,
   tipo_alma_arma varchar(50) NOT NULL,
   tipo_uso_arma varchar(50) NOT NULL,
   numero_raias varchar(4),
   sentido_raia_arma varchar(10),
   PRIMARY KEY (id),
   CONSTRAINT fk_arma_calibre FOREIGN KEY (calibre_id) REFERENCES calibres(id),
   CONSTRAINT fk_arma_marca FOREIGN KEY (marca_id) REFERENCES marcas(id)
);