create table COMUNIDADES
(
  NOMBRE VARCHAR2(200),
  CODIGO VARCHAR2(20) primary key
);
create table PROVINCIAS
(
 IDPROVINCIA INTEGER not null primary key,
 PROVINCIA   VARCHAR2(150),
 COMUNIDAD VARCHAR2(20) 
);

prompt Loading COMUNIDADES...
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Canarias', 'CAN');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Illes Balears', 'BAL');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Galicia', 'GAL');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('La Rioja', 'LRJ');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Principado de Asturias ', 'AST');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Cantabria', 'CTB');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('País Vasco', '38');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Comunidad Foral de Navarra', 'NVR');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Aragón', 'ARA');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Cataluña', 'CAT');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Castilla y León', 'CYL');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Comunidad de Madrid', 'MAD');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Comunitat Valenciana', 'VAL');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Región de Murcia', 'MUR');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Castilla - La Mancha', '22');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Extremadura', 'EXT');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Andalucía', 'AND');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Ciudad de Ceuta', 'CEU');
insert into COMUNIDADES (NOMBRE, CODIGO)
values ('Ciudad de Melilla', 'MEL');
commit;
prompt 19 records loaded

prompt Loading PROVINCIAS...
insert into provincias values(1, 'Álava', 'PVC');
insert into provincias values(2, 'Castellón', 'VAL');
insert into provincias values(3, 'León', 'CYL');
insert into provincias values(4, 'Salamanca', 'CYL');
insert into provincias values(5, 'Albacete', 'CLM');
insert into provincias values(6, 'Ceuta', 'CEU');
insert into provincias values(7, 'Lleida', 'CAT');
insert into provincias values(8, 'Segovia', 'CYL');
insert into provincias values(9, 'Alicante', 'VAL');
insert into provincias values(10, 'Ciudad Real', 'CLM');
insert into provincias values(11, 'Lugo', 'GAL');
insert into provincias values(12, 'Sevilla', 'AND');
insert into provincias values(13, 'Almería', 'AND');
insert into provincias values(14, 'Córdoba', 'AND');
insert into provincias values(15, 'Madrid', 'MAD');
insert into provincias values(16, 'Soria', 'CYL');
insert into provincias values(17, 'Asturias', 'AST');
insert into provincias values(18, 'A Coruña', 'GAL');
insert into provincias values(19, 'Málaga', 'AND');
insert into provincias values(20, 'Tarragona', 'CAT');
insert into provincias values(21, 'Ávila', 'CYL');
insert into provincias values(22, 'Cuenca', 'CLM');
insert into provincias values(23, 'Melilla', 'MEL');
insert into provincias values(24, 'S.C. Tenerife', 'CAN');
insert into provincias values(25, 'Badajoz', 'EXT');
insert into provincias values(26, 'Girona', 'CAT');
insert into provincias values(27, 'Murcia', 'MUR');
insert into provincias values(28, 'Teruel', 'ARA');
insert into provincias values(29, 'Baleares', 'BAL');
insert into provincias values(30, 'Granada', 'AND');
insert into provincias values(31, 'Navarra', 'NVR');
insert into provincias values(32, 'Toledo', 'CLM');
insert into provincias values(33, 'Barcelona', 'CAT');
insert into provincias values(34, 'Guadalajara', 'CLM');
insert into provincias values(35, 'Ourense', 'GAL');
insert into provincias values(36, 'Valencia', 'VAL');
insert into provincias values(37, 'Burgos', 'CYL');
insert into provincias values(38, 'Guipúzcoa', 'PVC');
insert into provincias values(39, 'Palencia', 'CYL');
insert into provincias values(40, 'Valladolid', 'CYL');
insert into provincias values(41, 'Cáceres', 'EXT');
insert into provincias values(42, 'Huelva', 'AND');
insert into provincias values(43, 'Las Palmas', 'CAN');
insert into provincias values(44, 'Vizcaya', 'PVC');
insert into provincias values(45, 'Cádiz', 'AND');
insert into provincias values(46, 'Huesca', 'ARA');
insert into provincias values(47, 'Pontevedra', 'GAL');
insert into provincias values(48, 'Zamora', 'CYL');
insert into provincias values(49, 'Cantabria', 'CTB');
insert into provincias values(50, 'Jaén', 'AND');
insert into provincias values(51, 'La Rioja', 'LRJ');
insert into provincias values(52, 'Zaragoza', 'ARA');