CREATE TABLE covidtests(
                            covidtest_id serial NOT NULL PRIMARY KEY,
                            covidtest_type varchar(30) NOT NULL,
                            covidtest_result varchar(30) NOT NULL,
                            covidtest_valid_duration int NOT NULL,
                            covidtest_date timestamp without time zone NOT NULL,
                            user_id varchar(50) NOT NULL
);



INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(1,'PCR','négatif',72,now(),'1');
INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(2,'PCR','négatif',72,now(),'2');
INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(3,'Antigénique','négatif',48,now(),'2');
INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(4,'Antigénique','négatif',48,now(),'1');
INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(5,'Antigénique','positif',48,now(),'3');