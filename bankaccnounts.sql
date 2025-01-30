DROP DATABASE IF EXISTS bank;
CREATE DATABASE bank;
USE bank;


CREATE USER georgaras@localhost IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON bank.* TO 'georgaras'@'localhost';

SHOW GRANTS FOR 'georgaras'@'localhost';

CREATE TABLE IF NOT EXISTS accounts (
    AccountID int(5) NOT NULL AUTO_INCREMENT,
    NAME varchar(50) NOT NULL,
    ADDRESS varchar(50) NOT NULL,
    NUMBER int(15) NOT NULL,
    mail varchar(50) NOT NULL,
    balance float(10) NOT NULL,
    active bit(1) ,
    PRIMARY KEY (AccountID)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


INSERT INTO accounts VALUES
    (1,'Δημήτρης Χατζηστεφάνου','Ευελιπίδων 69',2104769852,'dimixa@gmail.com',245.14,1),
    (2,'Στέφανος Χατζηπαύλου','Λ.Βασιλίσσης Σοφίας 64',2105874963,'stefxa@gmail.com',98.47,1),
    (3,'Παύλος Χατζηκώστας','Μιχαλακοπούλου 36',2106974851,'paulmi@gmail.com',25.49,1),
    (4,'Κωνσταντίνος Παπαδόπουλος','Υμηττού 46',2107256987,'konstpa@gmail.com',367.95,1)
;
