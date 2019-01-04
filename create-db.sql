SET DB_CLOSE_DELAY -1;         
;              
CREATE USER IF NOT EXISTS SA SALT 'f969fac56feff455' HASH '720852995cd963502a4a8d41e12f050d7f8fa0cc752504d3089e2eb61816c902' ADMIN;            
CREATE SEQUENCE PUBLIC.HIBERNATE_SEQUENCE START WITH 1;        
CREATE MEMORY TABLE PUBLIC.POST(
    ID INTEGER NOT NULL,
    DESCRIPTION VARCHAR(255),
    POST_TITLE VARCHAR(255),
    USER_ID INTEGER
);    
ALTER TABLE PUBLIC.POST ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.POST;     
CREATE MEMORY TABLE PUBLIC.USER(
    ID INTEGER NOT NULL,
    BIRTH_DATE TIMESTAMP,
    NAME VARCHAR(255)
);   
ALTER TABLE PUBLIC.USER ADD CONSTRAINT PUBLIC.CONSTRAINT_27 PRIMARY KEY(ID);   
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.USER;     
INSERT INTO PUBLIC.USER(ID, BIRTH_DATE, NAME) VALUES
(1, TIMESTAMP '2018-12-25 23:27:43.369', 'piotr');        
ALTER TABLE PUBLIC.POST ADD CONSTRAINT PUBLIC.FK72MT33DHHS48HF9GCQRQ4FXTE FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USER(ID) NOCHECK;             
