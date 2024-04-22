DROP TABLE IF EXISTS tax_returns;
DROP TABLE IF EXISTS tax_forms;
DROP TABLE IF EXISTS taxpayer_info;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP WITH TIME ZONE
);


CREATE TABLE taxpayer_info (
    taxpayer_info_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    first_name VARCHAR(50),
    middle_name VARCHAR(50),
    last_name VARCHAR(50),
    occupation VARCHAR(50),
    ssn CHAR(9) UNIQUE,
    date_of_birth DATE,
    phone_number VARCHAR(20),
    address JSONB
);

CREATE TABLE tax_forms (
    tax_form_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    status VARCHAR(15) DEFAULT 'pending',
    submitted_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    form_type VARCHAR(5),
    form_details JSONB
);


CREATE TABLE tax_returns (
    tax_return_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    total_income NUMERIC(10, 2),
    federal_tax_due_return NUMERIC(10, 2),
    status VARCHAR(15) DEFAULT 'pending',
    submitted_at TIMESTAMP WITH TIME ZONE,
    summary JSONB
);
