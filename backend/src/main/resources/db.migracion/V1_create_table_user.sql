CREATE TABLE tb_user (
id VARCHAR(255) NOT NULL,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
date_birth DATE NOT NULL,
CONSTRAINT pk_user PRIMARY KEY (id) 
);

ALTER TABLE tb_user ADD CONSTRAINT UC_TB_USER_EMAIL UNIQUE (email);
ALTER TABLE tb_user ADD CONSTRAINT UC_TB_USER_PASSWORD UNIQUE (password);