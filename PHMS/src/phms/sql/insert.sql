-- Add sql statments to insert values

INSERT INTO "VCHAWLA3"."PERSON" (PER_ID, PER_FIRSTNAME, PER_LASTNAME, PER_DATEOFBIRTH, PER_ADDRESS, PER_SEX, PER_PASSWORD) VALUES ('1', 'Sheldon', 'Cooper', TO_DATE('1984-05-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '2500 Sacramento, Apt 903, Santa Cruz, CA - 90021', 'M', 'password');
INSERT INTO "VCHAWLA3"."PERSON" (PER_ID, PER_FIRSTNAME, PER_LASTNAME, PER_DATEOFBIRTH, PER_ADDRESS, PER_SEX, PER_PASSWORD) VALUES ('2', 'Leonard', 'Hofstader', TO_DATE('1989-04-19 00:01:36', 'YYYY-MM-DD HH24:MI:SS'), '2500 Sacramento, Apt 904, Santa Cruz, CA - 90021', 'M', 'password');
INSERT INTO "VCHAWLA3"."PERSON" (PER_ID, PER_FIRSTNAME, PER_LASTNAME, PER_DATEOFBIRTH, PER_ADDRESS, PER_SEX, PER_PASSWORD) VALUES ('3', 'Penny', 'Hofstader', TO_DATE('1990-12-25 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '2500 Sacramento, Apt 904, Santa Cruz, CA - 90021', 'F', 'password');
INSERT INTO "VCHAWLA3"."PERSON" (PER_ID, PER_FIRSTNAME, PER_LASTNAME, PER_DATEOFBIRTH, PER_ADDRESS, PER_SEX, PER_PASSWORD) VALUES ('4', 'Amy', 'Farrahfowler', TO_DATE('1992-06-15 00:02:35', 'YYYY-MM-DD HH24:MI:SS'), '2500 Sacramento, Apt 905, Santa Cruz, CA - 90021', 'F', 'password');

INSERT INTO "VCHAWLA3"."PATIENT" (PAT_PERSON, PAT_SICK) VALUES ('1', '1');
INSERT INTO "VCHAWLA3"."PATIENT" (PAT_PERSON, PAT_SICK) VALUES ('2', '1');
INSERT INTO "VCHAWLA3"."PATIENT" (PAT_PERSON, PAT_SICK) VALUES ('3', '0');
INSERT INTO "VCHAWLA3"."PATIENT" (PAT_PERSON, PAT_SICK) VALUES ('4', '0');

INSERT INTO "VCHAWLA3"."HEALTH_SUPPORTER" (HS_SUPPORTER, HS_PATIENT, HS_DATEAUTHORIZED) VALUES ('2', '1', TO_DATE('2016-10-21 00:23:45', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "VCHAWLA3"."HEALTH_SUPPORTER" (HS_SUPPORTER, HS_PATIENT, HS_DATEAUTHORIZED) VALUES ('4', '1', TO_DATE('2016-10-21 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "VCHAWLA3"."HEALTH_SUPPORTER" (HS_SUPPORTER, HS_PATIENT, HS_DATEAUTHORIZED) VALUES ('3', '2', TO_DATE('2016-10-09 00:24:16', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "VCHAWLA3"."HEALTH_SUPPORTER" (HS_SUPPORTER, HS_PATIENT, HS_DATEAUTHORIZED) VALUES ('4', '3', TO_DATE('2016-10-21 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO "VCHAWLA3"."DISEASE" (DIS_DISEASENAME) VALUES ('Heart Disease');
INSERT INTO "VCHAWLA3"."DISEASE" (DIS_DISEASENAME) VALUES ('HIV');
INSERT INTO "VCHAWLA3"."DISEASE" (DIS_DISEASENAME) VALUES ('COPD');

INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_UPPERLIMIT, HOT_LOWERLIMIT, HOT_FREQUENCY) VALUES ('1', 'Weight', 'Heart Disease', '200', '120', '7');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_UPPERLIMIT, HOT_LOWERLIMIT, HOT_FREQUENCY) VALUES ('2', 'Blood Pressure (Systolic)', 'Heart Disease', '159', '140', '1');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_UPPERLIMIT, HOT_LOWERLIMIT, HOT_FREQUENCY) VALUES ('3', 'Blood Pressure (Diastolic)', 'Heart Disease', '99', '90', '1');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_UPPERLIMIT, HOT_FREQUENCY) VALUES ('4', 'Mood', 'Heart Disease', '1', '7');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_UPPERLIMIT, HOT_LOWERLIMIT, HOT_FREQUENCY) VALUES ('5', 'Weight', 'HIV', '200', '120', '7');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_FREQUENCY) VALUES ('6', 'Blood Pressure (Systolic)', 'HIV', '1');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_FREQUENCY) VALUES ('7', 'Blood Pressure (Diastolic)', 'HIV', '1');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_UPPERLIMIT, HOT_FREQUENCY) VALUES ('8', 'Pain', 'HIV', '5', '1');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_UPPERLIMIT, HOT_LOWERLIMIT, HOT_FREQUENCY) VALUES ('9', 'Oxygen Saturation', 'COPD', '99', '90', '1');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_DISEASE, HOT_UPPERLIMIT, HOT_LOWERLIMIT, HOT_FREQUENCY) VALUES ('10', 'Temperature', 'COPD', '100', '95', '1');
INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION_TYPE" (HOT_ID, HOT_NAME, HOT_UPPERLIMIT, HOT_LOWERLIMIT, HOT_FREQUENCY) VALUES ('11', 'Weight', '200', '100', '7');

INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION" (HO_PATIENT, HO_OBSERVATIONTYPE, HO_VALUE, HO_RECORDEDDATETIME) VALUES ('2', '6', '180', TO_DATE('2016-10-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
-- Issue with primary key
--INSERT INTO "VCHAWLA3"."HEALTH_OBSERVATION" (HO_PATIENT, HO_OBSERVATIONTYPE, HO_VALUE, HO_RECORDEDDATETIME) VALUES ('2', '6', '195', TO_DATE('2016-10-17 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));