CREATE TABLE credit_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Clave primaria de tipo BIGINT y autoincrementable
    name VARCHAR(255) NOT NULL UNIQUE,  -- Nombre del tipo de crédito, único
    max_term INT NOT NULL,  -- Plazo máximo en años
    min_interest_rate DECIMAL(5, 2) NOT NULL,  -- Tasa mínima de interés anual, con 2 decimales
    max_interest_rate DECIMAL(5, 2) NOT NULL,  -- Tasa máxima de interés anual, con 2 decimales
    max_financing_percentage DECIMAL(5, 2) NOT NULL  -- Porcentaje máximo de financiamiento, con 2 decimales
);


INSERT INTO credit_type (name, max_term, min_interest_rate, max_interest_rate, max_financing_percentage) VALUES
('Primera Vivienda', 30, 3.5, 5.0, 80.0),
('Segunda Vivienda', 20, 4.0, 6.0, 70.0),
('Propiedades Comerciales', 25, 5.0, 7.0, 60.0),
('Remodelación', 15, 4.5, 6.0, 50.0);
