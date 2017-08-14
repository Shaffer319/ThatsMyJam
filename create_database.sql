
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
  AlbumName VARCHAR(50) NOT NULL,
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

insert into Artist(ArtistName, ImageName)
values ('Halsey', 'Halsey.png')

insert into Artist(ArtistName, ImageName)
values ('alt-J', 'alt-J.png')

insert into Artist(ArtistName, ImageName)
values ('Metallica', 'Metallica.png')

insert into Artist(ArtistName, ImageName)
values ('Kid Cudi', 'Kid_Cudi.png')

insert into Artist(ArtistName, ImageName)
values ('Vicetone', 'Vicetone.png')

insert into Artist(ArtistName, ImageName)
values ('Run the Jewels', 'Run_the_Jewels.png')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Badlands', '1', '2015', 'Badlands.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Castle', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hold Me Down', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('New Americana', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Drive', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Roman Holiday', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Colors', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Coming Down', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Haunting', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Control', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Young God', '1', '1', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ghost', '1', '1', '2015')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Hopeless Fountain Kingdom', '1', '2017', 'HFK.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Prologue', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('100 Letters', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Eyes Closed', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Alone', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Now or Never', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Sorry', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Good Mourning', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Lie', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Walls Could Talk', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Bad at Love', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Strangers', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Devil In Me', '1', '2', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hopeless', '1', '2', '2017')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Relaxer', '2', '2017', 'Relaxer.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('3WW', '2', '3', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('In Cold Blood', '2', '3', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('House of the Rising Sun', '2', '3', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hit Me Like That Snare', '2', '3', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Deadcrush', '2', '3', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Adeline', '2', '3', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Last Year', '2', '3', '2017')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pleader', '2', '3', '2017')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('This Is All Yours', '2', '2014', 'TIAY.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Intro', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Arrival In Nara', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Nara', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Every Other Freckle', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Left Hand Free', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Garden of England', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Choice Kingdom', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hunger of the Pine', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Warm Foothills', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Gospel of John Hurt', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pusher', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Bloodflood Pt. II', '2', '4', '2014')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Leaving Nara', '2', '4', '2014')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Ride the Lightning', '3', '1984', 'RtL.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Fight Fire with Fire', '3', '5', '1984')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ride the Lightning', '3', '5', '1984')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('For Whom the Bell Tolls', '3', '5', '1984')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Fade to Black', '3', '5', '1984')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Trapped Under Ice', '3', '5', '1984')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Escape', '3', '5', '1984')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Creeping Death', '3', '5', '1984')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Call of Ktulu', '3', '5', '1984')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Load', '3', '1996', 'Load.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Aint Mine', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('2 X 4', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The House Jack Built', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Until It Sleeps', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('King Nothing', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hero of the Day', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Bleeding Me', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Cure', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Poor Twisted Me', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Wasting My Hate', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Mama Said', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Thorn Within', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ronnie', '3', '6', '1996')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Outlaw Torn', '3', '6', '1996')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Man on the Moon II: The Legend of Mr. Rager', '4', '2010', 'MotM.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Scott Mescudi vs. the World', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Revofev', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dont Play This Song', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('We Aite', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Mojo So Dope', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Erase Me', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Wildn Cuz Im Young', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Mood', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Maniac', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Mr. Rager', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('These Worries', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The End', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('All Along', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ghost', '4', '7', '2010')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Trapped In My Mind', '4', '7', '2010')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Aurora EP', '5', '2016', 'Aurora.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Bright Side', '5', '8', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dont You Run', '5', '8', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Siren', '5', '8', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Otherside', '5', '8', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Green Eyes', '5', '8', '2016')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Meow the Jewels', '6', '2015', 'Meow.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Meowpurrdy', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Oh My Darling Dont Meow', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pawfluffer Night', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Close Your Eyes and Meow to Fluff', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('All Meow Life', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Lie, Cheat, Meow', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Meowrly', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Paw Due Respect', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Snug Again', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Creown (The Alchemist Remix)', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Angelsnuggler', '6', '9', '2015')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Creown (3D Remix)', '6', '9', '2015')

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName)
values ('Run the Jewels 3', '6', '2016', 'RtJ3.png')

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Down', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Talk to Me', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Legend Has It', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Call Ticketron', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hey Kids', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Stay Gold', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dont Get Captured', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Thieves!', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('2100', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Panther Like a Panther', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Everybody Stay Calm', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Oh Mama', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Thursday in the Danger Room', '6', '10', '2016')
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('A Report to the Shareholders / Kill Your Masters', '6', '10', '2016')