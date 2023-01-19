/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`predstave` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `predstave`;



DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Administrator`(`AdministratorID`,`Ime`,`Prezime`,`Username`,`Password`) VALUES 
(1,'Srdjan','Medic','srki','srki123'),
(2,'Milica','Stojiljkovic','milica','milica123');



DROP TABLE IF EXISTS `Zanr`;

CREATE TABLE `Zanr` (
  `ZanrID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivZanra` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ZanrID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Zanr`(`ZanrID`,`NazivZanra`) VALUES 
(1,'Komedija'),
(2,'Tragedija'),
(3,'Istorija'),
(4,'Mjuzikl');


DROP TABLE IF EXISTS `Sala`;

CREATE TABLE `Sala` (
  `SalaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivSale` VARCHAR(50) NOT NULL,
  `Kapacitet` INT(7) NOT NULL,
  PRIMARY KEY (`SalaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Sala`(`SalaID`,`NazivSale`,`Kapacitet`) VALUES 
(1,'Marilyn Monroe',100),
(2,'Marlon Brando',200),
(3,'John Travolta',150),
(4,'Meryl Streep',120);



DROP TABLE IF EXISTS `Glumac`;

CREATE TABLE `Glumac` (
  `GlumacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ImeGlumca` VARCHAR(20) NOT NULL,
  `PrezimeGlumca` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`GlumacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Glumac`(`GlumacID`,`ImeGlumca`,`PrezimeGlumca`,`Email`,`Telefon`) VALUES 
(1,'Milos', 'Bikovic', 'milos@gmail.com', '0631231234'),
(2,'Nina', 'Jankovic', 'nina@gmail.com', '0654645434'),
(3,'Sloboda', 'Micanovic', 'sloboda@gmail.com', '0641235153'),
(4,'Mihajlo', 'Janketic', 'mihajlo@gmail.com', '064543563');


DROP TABLE IF EXISTS `Predstava`;

CREATE TABLE `Predstava` (
  `PredstavaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivPredstave` VARCHAR(50) NOT NULL,
  `DatumVremeOdrzavanja` DATETIME NOT NULL,  
  `Opis` VARCHAR(200) NOT NULL,
  `BrojCinova` INT(7) UNSIGNED NOT NULL,
  `Reditelj` VARCHAR(70) COLLATE utf8_unicode_ci NOT NULL,
  `SalaID` BIGINT(10) UNSIGNED NOT NULL,
  `ZanrID` BIGINT(10) UNSIGNED NOT NULL,
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PredstavaID`),
  CONSTRAINT `fk_sala_id` FOREIGN KEY (`SalaID`) REFERENCES `Sala` (`SalaID`),
  CONSTRAINT `fk_zanr_id` FOREIGN KEY (`ZanrID`) REFERENCES `Zanr` (`ZanrID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT  INTO `Predstava` VALUES 
(1,'Cigani lete u nebo','2022-06-28 20:00:00','Pozorisni komad Aleksandra Popovica.',5,'Milan Neskovic',1,1,1);


DROP TABLE IF EXISTS `Uloga`;

CREATE TABLE `Uloga` (
  `PredstavaID` BIGINT(10) UNSIGNED NOT NULL,
  `RbUloge` INT(7) NOT NULL,
  `NazivUloge` VARCHAR(50) NOT NULL,
  `OpisUloge` VARCHAR(200) NOT NULL,
  `GlumacID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PredstavaID`,`RbUloge`),
  CONSTRAINT `fk_predstava_id` FOREIGN KEY (`PredstavaID`) REFERENCES `Predstava` (`PredstavaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_glumac_id` FOREIGN KEY (`GlumacID`) REFERENCES `Glumac` (`GlumacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Uloga` VALUES 
(1,1,'Momcilo Jabucilo','Vojnik',1),
(1,2,'Majka Janja','Majka vojnika',2),
(1,3,'Deli Jova','Doktor',4);





/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
