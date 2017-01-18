create user pocockn;
alter user pocockn superuser;
drop database footballPrototype;
create database footballPrototype;
grant all privileges on database footballPrototype to pocockn;

drop database test;
create database test;
grant all privileges on database test to pocockn;
grant all privileges on database test to pocockn;