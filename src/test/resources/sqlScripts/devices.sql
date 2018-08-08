TRUNCATE devices CASCADE;
INSERT INTO devices (id, name) values (1, 'Mercedes');
SELECT setval('devices_id_seq'::REGCLASS, 2);