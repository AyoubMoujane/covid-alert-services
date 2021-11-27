CREATE TABLE users(
                      user_id serial NOT NULL PRIMARY KEY,
                      first_name varchar(30) NOT NULL,
                      last_name varchar(30) NOT NULL,
                      email varchar(80) NOT NULL,
                      phone_number varchar(20) NOT NULL,
                      password varchar(100) NOT NULL
);

CREATE TABLE locations(
                          location_id serial PRIMARY KEY,
                          latitude numeric(18, 16) NOT NULL,
                          longitude numeric(18, 16) NOT NULL,
                          location_date timestamp with time zone NOT NULL,
                          user_id varchar(30)


);





INSERT INTO users(user_id,first_name,last_name,email,phone_number,password) VALUES(1,'Tom','Robinson','tom.rob@yopmail.com','+15103754657','123456');
INSERT INTO users(user_id,first_name,last_name,email,phone_number,password) VALUES(2,'Bob','Boby','bob.rob@yopmail.com','+15103754657','123456');
INSERT INTO users(user_id,first_name,last_name,email,phone_number,password) VALUES(3,'Jean','Dupont','bob.rob@yopmail.com','+15103754657','123456');
INSERT INTO users(user_id,first_name,last_name,email,phone_number,password) VALUES(4,'Jacques','Durant','bob.rob@yopmail.com','+15103754657','123456');


INSERT INTO locations(location_id,latitude,longitude,location_date,user_id) VALUES(1000,43.63746472422702,3.8409670228559136,now(),'un');
INSERT INTO locations(location_id,latitude,longitude,location_date,user_id) VALUES(1001,46,46,now(),'deux');
INSERT INTO locations(location_id,latitude,longitude,location_date,user_id) VALUES(1002,99,99,now(),'trois');
INSERT INTO locations(location_id,latitude,longitude,location_date,user_id) VALUES(1003,46,46,'2019-11-15T20:48:08.831+00:00','quatre');



