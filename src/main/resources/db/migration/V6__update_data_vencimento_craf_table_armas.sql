ALTER TABLE armas
ADD COLUMN vencimento_indeterminado TINYINT DEFAULT 0,
ADD COLUMN data_vencimento_craf DATETIME;