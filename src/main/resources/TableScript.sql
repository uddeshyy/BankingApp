DROP DATABASE IF EXISTS banking_db;
CREATE DATABASE banking_db;
USE banking_db;

CREATE TABLE account(
	account_no INT AUTO_INCREMENT,
	name VARCHAR(30),
	age INT,
	gender ENUM('male','female','others'),
	username VARCHAR(30),
	password VARCHAR(30),
	balance DOUBLE,
	CONSTRAINT account_no_pk PRIMARY KEY (account_no)
);

CREATE TABLE transaction (
	transaction_id VARCHAR(50),
	date_time DATETIME,
	from_acc INT,
	to_acc INT,
	amount DOUBLE(10,2),
	remarks VARCHAR(50),
	CONSTRAINT transaction_id_pk PRIMARY KEY (transaction_id),
	CONSTRAINT fk_from_acc FOREIGN KEY (from_acc) REFERENCES account(account_no),
	CONSTRAINT fk_to_acc FOREIGN KEY (to_acc) REFERENCES account(account_no)
);

CREATE TABLE issue (
	issue_no INT AUTO_INCREMENT,
	transaction_id VARCHAR(50),
	due_date DATE,
	CONSTRAINT issue_no_pk PRIMARY KEY (issue_no),
	CONSTRAINT fk_transaction FOREIGN KEY (transaction_id) REFERENCES transaction(transaction_id)
);


COMMIT;

SELECT * FROM account;
SELECT * FROM transaction;
SELECT * FROM issue;
