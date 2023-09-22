CREATE TABLE empresas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    saldo DECIMAL(8,2) NOT NULL,
    taxa_administracao DECIMAL(2,1) NOT NULL,

    PRIMARY KEY(id)
);