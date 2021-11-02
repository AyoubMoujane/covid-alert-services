CREATE TABLE locations(
                          location_id serial PRIMARY KEY,
                          latitude numeric(18, 16) NOT NULL,
                          longitude numeric(18, 16) NOT NULL,
                          location_date timestamp without time zone NOT NULL
);
INSERT INTO locations(latitude,longitude,location_date) VALUES(43.63746472422702,3.8409670228559136,now());