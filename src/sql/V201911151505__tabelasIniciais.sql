CREATE TABLE PALAVRAS( 
   id INT auto_increment, 
   palavra VARCHAR(250) NOT NULL
);

CREATE TABLE LETRAS( 
   id INT auto_increment, 
   letra VARCHAR(250) NOT NULL,
   certo bigint not null,
   errado bigint not null
);
