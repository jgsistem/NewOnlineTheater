CREATE TABLE customer (
  Id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  Name VARCHAR(50) NOT NULL,
  Email VARCHAR(50) NOT NULL,
  Status INT NOT NULL,
  StatusExpirationDate DATE NOT NULL,
  MoneySpent DOUBLE,
  PRIMARY KEY(Id)

) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO customer(Id, Name, Email, Status,StatusExpirationDate,MoneySpent)
VALUES(1,'Juan', 'jgsistem@gmail.com',1,'1995-01-29',1);