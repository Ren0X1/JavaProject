﻿create table COMUNIDADES
(
  NOMBRE VARCHAR2(200),
  CODIGO VARCHAR2(20)
);
create table PROVINCIAS
(
 IDPROVINCIA INTEGER not null,
 PROVINCIA   VARCHAR2(150),
 COMUNIDAD VARCHAR2(20) 
);