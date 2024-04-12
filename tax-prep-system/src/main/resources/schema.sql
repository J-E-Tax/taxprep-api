drop table if EXISTS users;
drop table if EXISTS taxpayer_info;
drop table if EXISTS tax_returns;

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
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


CREATE TABLE tax_returns (
    tax_return_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    total_income NUMERIC(10, 2),
    federal_tax_due_return NUMERIC(10, 2),
    status VARCHAR(15) DEFAULT 'pending',
    submitted_at TIMESTAMP WITH TIME ZONE,
    summary JSONB
);
