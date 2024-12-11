CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    income DECIMAL(15, 2) NOT NULL,  -- Usamos DECIMAL para valores monetarios
    registration_date DATE NOT NULL
);

CREATE TABLE credit_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,  -- Correo electrónico, único
    requested_amount DECIMAL(15, 2) NOT NULL,  -- Monto solicitado, usando DECIMAL para valores monetarios
    term_in_years INT NOT NULL,  -- Plazo en años
    credit_type_id BIGINT NOT NULL,  -- Referencia al tipo de crédito
    FOREIGN KEY (credit_type_id) REFERENCES credit_types(id)  -- Llave foránea que referencia a la tabla credit_types
);
