CREATE TABLE devices (
  id serial primary key,
  name varchar(100) not null
);

CREATE TABLE gps_data (
  id serial primary key,
  latitude double precision not null,
  longitude double precision not null,
  time timestamp not null,
  radius double precision not null,
  device_id integer references devices not null
);