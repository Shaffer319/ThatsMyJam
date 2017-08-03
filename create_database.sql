
# Drop database. This will clear all the data in the database
drop database if exists thatsmyjam;

create database thatsmyjam;

use thatsmyjam;

# add password
create table User (
  UserID INT NOT NULL AUTO_INCREMENT,
  Email VARCHAR(50),
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  
  PRIMARY KEY(UserID) 
);

create table UserPass (
  Username varchar(15) NOT NULL PRIMARY KEY,
  Password varchar(15) NOT NULL
);

