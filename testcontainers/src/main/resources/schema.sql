CREATE TABLE customers (
  id NUMBER NOT NULL,
  name VARCHAR2(255) NOT NULL,
  email VARCHAR2(255) NOT NULL,
  CONSTRAINT customers_pk PRIMARY KEY (id),
  CONSTRAINT customers_email_uk UNIQUE (email)
);
