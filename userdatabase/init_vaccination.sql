CREATE TABLE vaccinations(
                             vaccination_id serial NOT NULL PRIMARY KEY,
                             vaccine_name varchar(30) NOT NULL,
                             vaccine_type varchar(30) NOT NULL,
                             target_disease varchar(30) NOT NULL,
                             vaccination_center varchar(80) NOT NULL,
                             country varchar(80) NOT NULL,
                             vaccination_date timestamp NOT NULL,
                             number_of_doses int NOT NULL,
                             user_id varchar(50) NOT NULL
);



INSERT INTO vaccinations(vaccination_id,vaccine_name,vaccine_type,target_disease,vaccination_center,country,vaccination_date,number_of_doses,user_id) VALUES(1,'Covid-19 vaccines','pfizer','COVID-19','Mairie de Montpellier','FR','2004-05-23T14:25:10',1,'1');
INSERT INTO vaccinations(vaccination_id,vaccine_name,vaccine_type,target_disease,vaccination_center,country,vaccination_date,number_of_doses,user_id) VALUES(2,'Covid-19 vaccines','moderna','COVID-19','Mairie de Montpellier','FR','2004-05-23T14:25:10',1,'2');
INSERT INTO vaccinations(vaccination_id,vaccine_name,vaccine_type,target_disease,vaccination_center,country,vaccination_date,number_of_doses,user_id) VALUES(3,'Covid-19 vaccines','moderna','COVID-19','Vailhauques','FR','2004-05-23T14:25:10',2,'2');
INSERT INTO vaccinations(vaccination_id,vaccine_name,vaccine_type,target_disease,vaccination_center,country,vaccination_date,number_of_doses,user_id) VALUES(4,'Covid-19 vaccines','pfizer','COVID-19','Vailhauques','FR','2004-05-23T14:25:10',2,'1');
INSERT INTO vaccinations(vaccination_id,vaccine_name,vaccine_type,target_disease,vaccination_center,country,vaccination_date,number_of_doses,user_id) VALUES(5,'Covid-19 vaccines','pfizer','COVID-19','Mairie de Montpellier','FR','2004-05-23T14:25:10',1,'3');