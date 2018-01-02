CREATE TABLE filme(
   id        INT              NOT NULL IDENTITY,
   nome      VARCHAR (250)    NOT NULL,
   ano       INT              NOT NULL,
   diretor  VARCHAR (250) ,
   PRIMARY KEY (id)
);