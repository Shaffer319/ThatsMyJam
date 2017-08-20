
# Drop database. This will clear all the data in the database
drop database if exists thatsmyjam;

create database thatsmyjam;

use thatsmyjam;

CREATE TABLE User (
  UserID INT NOT NULL AUTO_INCREMENT,
  Email VARCHAR(50) NOT NULL,
  Password varchar(15) NOT NULL,
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  PRIMARY KEY(UserID)
);

CREATE TABLE UserRole (
  Email VARCHAR(50) not null,
  Rolename VARCHAR(15) not null,
  PRIMARY KEY (Email, Rolename)
);

# Adding user to the 2 relational tables
# These tables are linked via the primary key UserName
insert into User( Email, Password, FirstName, LastName)
values ('root@thatsmyjam.com', 'sesame', 'root', 'root');

insert into UserRole(Email, Rolename)
value ('root@thatsmyjam.com', 'admin');
 

create table Artist (
  ArtistID INT NOT NULL AUTO_INCREMENT,
  ArtistName VARCHAR(30) NOT NULL,
  ImageName VARCHAR(30),
  PRIMARY KEY(ArtistID)
);

create table Album (
  AlbumID INT NOT NULL AUTO_INCREMENT,
  AlbumName VARCHAR(50) NOT NULL,
  ArtistID INT NOT NULL,
  ReleaseYear INT,
  ImageName VARCHAR(30),
  AlbumPrice DECIMAL(7,2) NOT NULL,
  PRIMARY KEY(AlbumID)
);

create table Song (
  SongID INT NOT NULL AUTO_INCREMENT,
  SongName VARCHAR(100) NOT NULL,
  ArtistID INT,
  AlbumID INT,
  ReleaseYear INT,
  PRIMARY KEY(SongID),
  FOREIGN KEY(ArtistID) REFERENCES Artist(ArtistID)
);

CREATE TABLE OwnedSongs (
  UserID INT NOT NULL,
  SongID INT NOT NULL,
  FOREIGN KEY(UserID) REFERENCES User(UserID),
  FOREIGN KEY(SongID) REFERENCES Song(SongID)
);

create table Playlist (
  PlaylistID INT NOT NULL AUTO_INCREMENT,
  UserID INT NOT NULL,
  PlaylistName VARCHAR(30) NOT NULL,
  PRIMARY KEY(PlaylistID),
  FOREIGN KEY(UserID) REFERENCES User(UserID)
);


# Artists
insert into Artist(ArtistName, ImageName)
values ('Halsey', 'Halsey.jpg');
insert into Artist(ArtistName, ImageName)
values ('alt-J', 'alt-J.jpg');
insert into Artist(ArtistName, ImageName)
values ('Metallica', 'Metallica.jpg');
insert into Artist(ArtistName, ImageName)
values ('Kid Cudi', 'Kid_Cudi.jpg');
insert into Artist(ArtistName, ImageName)
values ('Vicetone', 'Vicetone.jpg');
insert into Artist(ArtistName, ImageName)
values ('Run the Jewels', 'Run_the_Jewels.png');
insert into Artist(ArtistName, ImageName)
values ('Avicii', 'Avicii.jpg');
insert into Artist(ArtistName, ImageName)
values ('Calvin Harris', 'CalvinHarris.jpg');
insert into Artist(ArtistName, ImageName)
values ('The Weeknd', 'Weeknd.jpg');
insert into Artist(ArtistName, ImageName)
values ('MGMT', 'MGMT.jpg');
insert into Artist(ArtistName, ImageName)
values ('Blink 182', 'Blink182.jpg');
insert into Artist(ArtistName, ImageName)
value  ('Clean Bandit', 'CleanBandit.jpg');

# Albums/Songs
insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName, AlbumPrice)
values ('Badlands', '1', '2015', 'Badlands.png', '9.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Castle', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hold Me Down', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('New Americana', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Drive', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Roman Holiday', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Colors', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Coming Down', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Haunting', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Control', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Young God', '1', '1', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ghost', '1', '1', '2015');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName, AlbumPrice)
values ('Hopeless Fountain Kingdom', '1', '2017', 'HFK.jpg','12.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Prologue', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('100 Letters', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Eyes Closed', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Alone', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Now or Never', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Sorry', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Good Mourning', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Lie', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Walls Could Talk', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Bad at Love', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Strangers', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Devil In Me', '1', '2', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hopeless', '1', '2', '2017');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Relaxer', '2', '2017', 'Relaxer.jpg','7.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('3WW', '2', '3', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('In Cold Blood', '2', '3', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('House of the Rising Sun', '2', '3', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hit Me Like That Snare', '2', '3', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Deadcrush', '2', '3', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Adeline', '2', '3', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Last Year', '2', '3', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pleader', '2', '3', '2017');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('This Is All Yours', '2', '2014', 'TIAY.jpg','7.62');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Intro', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Arrival In Nara', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Nara', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Every Other Freckle', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Left Hand Free', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Garden of England', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Choice Kingdom', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hunger of the Pine', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Warm Foothills', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Gospel of John Hurt', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pusher', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Bloodflood Pt. II', '2', '4', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Leaving Nara', '2', '4', '2014');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Ride the Lightning', '3', '1984', 'RtL.jpg','9.49');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Fight Fire with Fire', '3', '5', '1984');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ride the Lightning', '3', '5', '1984');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('For Whom the Bell Tolls', '3', '5', '1984');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Fade to Black', '3', '5', '1984');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Trapped Under Ice', '3', '5', '1984');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Escape', '3', '5', '1984');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Creeping Death', '3', '5', '1984');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Call of Ktulu', '3', '5', '1984');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Load', '3', '1996', 'Load.jpg','9.49');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Aint Mine', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('2 X 4', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The House Jack Built', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Until It Sleeps', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('King Nothing', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hero of the Day', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Bleeding Me', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Cure', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Poor Twisted Me', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Wasting My Hate', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Mama Said', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Thorn Within', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ronnie', '3', '6', '1996');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Outlaw Torn', '3', '6', '1996');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Man on the Moon II: The Legend of Mr. Rager', '4', '2010', 'MotM.jpg','8.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Scott Mescudi vs. the World', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Revofev', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dont Play This Song', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('We Aite', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Mojo So Dope', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Erase Me', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Wildn Cuz Im Young', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Mood', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Maniac', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Mr. Rager', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('These Worries', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The End', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('All Along', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ghost', '4', '7', '2010');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Trapped In My Mind', '4', '7', '2010');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Aurora EP', '5', '2016', 'Aurora.jpg','4.45');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Bright Side', '5', '8', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dont You Run', '5', '8', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Siren', '5', '8', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Otherside', '5', '8', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Green Eyes', '5', '8', '2016');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Meow the Jewels', '6', '2015', 'Meow.png','9.49');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Meowpurrdy', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Oh My Darling Dont Meow', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pawfluffer Night', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Close Your Eyes and Meow to Fluff', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('All Meow Life', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Lie, Cheat, Meow', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Meowrly', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Paw Due Respect', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Snug Again', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Creown (The Alchemist Remix)', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Angelsnuggler', '6', '9', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Creown (3D Remix)', '6', '9', '2015');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Run the Jewels 3', '6', '2016', 'RtJ3.jpg','8.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Down', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Talk to Me', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Legend Has It', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Call Ticketron', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hey Kids', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Stay Gold', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dont Get Captured', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Thieves!', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('2100', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Panther Like a Panther', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Everybody Stay Calm', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Oh Mama', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Thursday in the Danger Room', '6', '10', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('A Report to the Shareholders / Kill Your Masters', '6', '10', '2016');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Stories', '7', '2015', 'Stories.png','9.49');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Waiting for Love', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Talk to Myself', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Touch Me', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ten More Days', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('For a Better Day', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Broken Arrows', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('True Believer', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('City Lights', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pure Grinding', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Sunset Jesus', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Cant Catch Me', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Somewhere in Stockholm', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Trouble', '7', '11', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Gonna Love Ya', '7', '11', '2015');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('True', '7', '2013', 'True.png','9.49');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Wake Me Up', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('You Make Me', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hey Brother', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Addicted To You', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dear Boy', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Liar Liar', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Shame on Me', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Lay Me Down', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hope Theres Someone', '7', '12', '2013');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Heart Upon My Sleeve', '7', '12', '2013');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Funk Wav Bounces Vol. 1', '8', '2017', 'FWBV1.jpg','9.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Slide', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Cash Out', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Heatstoke', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Rollin', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Prayers Up', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Holiday', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Skrt on Me', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Feels', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Faking It', '8', '13', '2017');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hard to Love', '8', '13', '2017');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Motion', '8', '2014', 'Motion.png','11.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Faith', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Under Control', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Blame', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Love Now', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Slow Acid', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Outside', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('It Was You', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Summer', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Overdrive', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ecstasy', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pray to God', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Open Wide', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Together', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Burnin', '8', '14', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dollar Signs', '8', '14', '2014');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Starboy', '9', '2016', 'Starboy.png','13.49');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Starboy', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Party Monster', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('False Alarm', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Reminder', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Rockin', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Secrets', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('True Colors', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Stargirl Interlude', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Sidewalks', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Six Feet Under', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Love to Lay', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('A Lonely Night', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Attention', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Ordinary Life', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Nothing Without You', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('All I Know', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Die for You', '9', '15', '2016');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('I Feel It Coming', '9', '15', '2016');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Beauty Behind the Madness', '9', '2015', 'BBtM.png','11.49');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Real Life', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Losers', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Tell Your Friends', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Often', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Hills', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Acquainted', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Cant Feel My Face', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Shameless', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Earned It', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('In the Night', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('As You Are', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dark Times', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Prisoner', '9', '16', '2015');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Angel', '9', '16', '2015');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Time to Pretend', '10', '2005', 'TimeToPretend.jpg','5.34');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Time to Pretend', '10', '17', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Boogie Down', '10', '17', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Destrokk', '10', '17', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Love Always Remains', '10', '17', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Indie Rokkers', '10', '17', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Kids', '10', '17', '2005');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Climbing to New Lows', '10', '2005', 'ClimbToNewLows.jpg','3.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Intro(Come on Christmas)', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('We Care', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Money to Burn', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Hot Love Drama', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('The Kids Quartet', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Kids', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Honey Bunny', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Greyhoundredux', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Grutu (Just Becuz)', '10', '18', '2005');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('We Dont Care', '10', '18', '2005');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('Dude Ranch', '11', '1997', 'DudeRanch.jpg','5.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Pathetic', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Voyeur', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dammit', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Boring', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Waggy', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Enthused', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Untitled', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Apple Shampoo', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Emo', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Josie', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('A New Hope', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Degenerate', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Lemmings', '11', '19', '1997');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Im Sorry', '11', '19', '1997');

insert into Album(AlbumName, ArtistID, ReleaseYear, ImageName,AlbumPrice)
values ('New Eyes', '12', '2014', 'NewEyes.png','7.99');

insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Mozarts House', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Extraordinary', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Dust Clears', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Rather Be', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('A+E', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Come Over', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Cologne', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Telephone Banking', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Up Again', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Heart on Fire', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('New Eyes', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Birch', '12', '20', '2014');
insert into Song(SongName, ArtistID, AlbumID, ReleaseYear)
values ('Outro Movement III', '12', '20', '2014');
