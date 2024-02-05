CREATE TABLE tb_account (
id VARCHAR(255) NOT NULL,
date_creation DATE NOT NULL,
plan VARCHAR(255) NOT NULL,
user_id VARCHAR(255) NOT NULL,
CONSTRAINT pk_account PRIMARY KEY (id)
);

ALTER TABLE tb_account ADD CONSTRAINT FK_TB_ACCOUNT_ON_TB_USER FOREIGN KEY (user_id);