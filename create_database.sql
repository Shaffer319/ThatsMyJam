
# Drop database. This will clear all the data in the database
drop database if exists thatsmyjam;

create database thatsmyjam;

use thatsmyjam;

# add password
create table User (
  UserID INT NOT NULL AUTO_INCREMENT,
  Email VARCHAR(50),
  Password VARCHAR(50),
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  PRIMARY KEY(UserID) 
);

create table Artist (
  ArtistID INT NOT NULL AUTO_INCREMENT,
  ArtistName VARCHAR(30) NOT NULL,
  ImageName VARCHAR(30),
  PRIMARY KEY(ArtistID)
);

create table Album (
  AlbumID INT NOT NULL AUTO_INCREMENT,
  AlbumName VARCHAR(30) NOT NULL,
  ArtistID VARCHAR(30) NOT NULL,
  ReleaseYear INT,
  ImageName VARCHAR(30),
  PRIMARY KEY(AlbumID)
);

create table Song (
  SongID INT NOT NULL AUTO_INCREMENT,
  SongName VARCHAR(30) NOT NULL,
  ArtistID INT,
  AlbumID INT,
  ReleaseYear INT,
  PRIMARY KEY(SongID),
  FOREIGN KEY(ArtistID) REFERENCES Artist(ArtistID)
);

create table Playlist (
  PlaylistID INT NOT NULL AUTO_INCREMENT,
  UserID varchar(15) NOT NULL,
  PlaylistName varchar(30) NOT NULL,
  PRIMARY KEY(PlaylistID),
  FOREIGN KEY(UserID) REFERENCES User(UserID)
)

