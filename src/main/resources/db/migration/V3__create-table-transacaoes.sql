CREATE TABLE transacoes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_cliente BIGINT NOT NULL,
    id_empresa BIGINT NOT NULL,
    data_hora TIMESTAMP NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(id_cliente) REFERENCES clientes(id),
    FOREIGN KEY(id_empresa) REFERENCES empresas(id)
);