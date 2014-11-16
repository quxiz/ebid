SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `ebid` ;
CREATE SCHEMA IF NOT EXISTS `ebid` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ebid` ;

-- -----------------------------------------------------
-- Table `ebid`.`Member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Member` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Member` (
  `memberID` BIGINT NOT NULL AUTO_INCREMENT,
  `userID` VARCHAR(45) NULL,
  `password` TEXT NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `address` TEXT NULL,
  `country` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phoneNo` VARCHAR(45) NULL,
  `activated` TINYINT(1) NULL,
  `timestamp` TIMESTAMP NULL,
  `paymentAccount` VARCHAR(45) NULL,
  `receivingAccount` VARCHAR(45) NULL,
  `blacklisted` TINYINT(1) NULL,
  `activateKey` TEXT NULL,
  PRIMARY KEY (`memberID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`Blacklist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Blacklist` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Blacklist` (
  `blacklistID` BIGINT NOT NULL AUTO_INCREMENT,
  `memberID` BIGINT NOT NULL,
  `detail` TEXT NULL,
  `status` ENUM('NORMAL','BLACKLIST') NULL,
  `timestamp` TIMESTAMP NULL,
  PRIMARY KEY (`blacklistID`, `memberID`),
  INDEX `fk_Blacklist_Member_idx` (`memberID` ASC),
  CONSTRAINT `fk_Blacklist_Member`
    FOREIGN KEY (`memberID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Item` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Item` (
  `itemID` BIGINT NOT NULL AUTO_INCREMENT,
  `sellerID` BIGINT NOT NULL,
  `title` VARCHAR(80) NULL,
  `condition` VARCHAR(45) NULL,
  `specific` TEXT NULL,
  `detail` TEXT NULL,
  `catagory` TEXT NULL,
  `sellingType` ENUM('BID', 'BUY') NULL,
  `price` DOUBLE NULL,
  `quantity` MEDIUMTEXT NULL,
  `startTime` TIMESTAMP NULL,
  `endTime` TIMESTAMP NULL,
  `paymentMethod` TEXT NULL,
  `shippingService` TEXT NULL,
  `shippingCost` DOUBLE NULL,
  `packageDetail` TEXT NULL,
  `returnPolicy` TEXT NULL,
  `timestamp` TIMESTAMP NULL,
  `delivery` TEXT NULL,
  PRIMARY KEY (`itemID`),
  INDEX `fk_Item_Member1_idx` (`sellerID` ASC),
  CONSTRAINT `fk_Item_Member1`
    FOREIGN KEY (`sellerID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`Photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Photo` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Photo` (
  `photoID` BIGINT NOT NULL AUTO_INCREMENT,
  `itemID` BIGINT NOT NULL,
  `photoURL` TEXT NULL,
  PRIMARY KEY (`photoID`, `itemID`),
  INDEX `fk_Photo_Item1_idx` (`itemID` ASC),
  CONSTRAINT `fk_Photo_Item1`
    FOREIGN KEY (`itemID`)
    REFERENCES `ebid`.`Item` (`itemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Comment` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Comment` (
  `commentID` BIGINT NOT NULL AUTO_INCREMENT,
  `parentID` BIGINT NULL,
  `itemID` BIGINT NOT NULL,
  `commenterID` BIGINT NOT NULL,
  `commentDetail` TEXT NULL,
  `timestamp` TIMESTAMP NULL,
  PRIMARY KEY (`commentID`),
  INDEX `fk_Post_Member1_idx` (`commenterID` ASC),
  INDEX `fk_Post_Item1_idx` (`itemID` ASC),
  INDEX `fk_Comment_Comment1_idx` (`parentID` ASC),
  CONSTRAINT `fk_Post_Member1`
    FOREIGN KEY (`commenterID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Post_Item1`
    FOREIGN KEY (`itemID`)
    REFERENCES `ebid`.`Item` (`itemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Comment1`
    FOREIGN KEY (`parentID`)
    REFERENCES `ebid`.`Comment` (`commentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`AutoBid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`AutoBid` ;

CREATE TABLE IF NOT EXISTS `ebid`.`AutoBid` (
  `itemID` BIGINT NOT NULL,
  `bidderID` BIGINT NOT NULL,
  `maxBid` DOUBLE NULL,
  `bidIncrement` DOUBLE NULL,
  `timestamp` TIMESTAMP NULL,
  INDEX `fk_AutoBid_Member1_idx` (`bidderID` ASC),
  PRIMARY KEY (`itemID`),
  CONSTRAINT `fk_AutoBid_Member1`
    FOREIGN KEY (`bidderID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AutoBid_Item1`
    FOREIGN KEY (`itemID`)
    REFERENCES `ebid`.`Item` (`itemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`BidSchedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`BidSchedule` ;

CREATE TABLE IF NOT EXISTS `ebid`.`BidSchedule` (
  `itemID` BIGINT NOT NULL,
  `endTime` TIMESTAMP NULL,
  `completed` TINYINT(1) NULL,
  PRIMARY KEY (`itemID`),
  CONSTRAINT `fk_BidHistory_Item1`
    FOREIGN KEY (`itemID`)
    REFERENCES `ebid`.`Item` (`itemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Transaction` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Transaction` (
  `transactionID` BIGINT NOT NULL AUTO_INCREMENT,
  `sellerID` BIGINT NOT NULL,
  `buyerID` BIGINT NOT NULL,
  `itemID` BIGINT NOT NULL,
  `quantity` MEDIUMTEXT NULL,
  `price` DOUBLE NULL,
  `detail` TEXT NULL,
  `sellingType` ENUM('BID','BUY') NULL,
  `delivery` TEXT NULL,
  `timestamp` TIMESTAMP NULL,
  `completed` TINYINT(1) NULL,
  PRIMARY KEY (`transactionID`),
  INDEX `fk_Transaction_Member1_idx` (`buyerID` ASC),
  INDEX `fk_Transaction_Member2_idx` (`sellerID` ASC),
  INDEX `fk_Transaction_Item1_idx` (`itemID` ASC),
  CONSTRAINT `fk_Transaction_Member1`
    FOREIGN KEY (`buyerID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Member2`
    FOREIGN KEY (`sellerID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Item1`
    FOREIGN KEY (`itemID`)
    REFERENCES `ebid`.`Item` (`itemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`Complaint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Complaint` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Complaint` (
  `complaintID` BIGINT NOT NULL AUTO_INCREMENT,
  `complainterID` BIGINT NOT NULL,
  `complaintTitle` TEXT NULL,
  `complaintDetail` TEXT NULL,
  `complaintTimestamp` TIMESTAMP NULL,
  `solverID` BIGINT NULL,
  `solveDetail` TEXT NULL,
  `solveTimestamp` TIMESTAMP NULL,
  PRIMARY KEY (`complaintID`),
  INDEX `fk_Complaint_Member1_idx` (`complainterID` ASC),
  CONSTRAINT `fk_Complaint_Member1`
    FOREIGN KEY (`complainterID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`Message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Message` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Message` (
  `messageID` BIGINT NOT NULL AUTO_INCREMENT,
  `senderID` BIGINT NOT NULL,
  `receivingID` BIGINT NOT NULL,
  `message` TEXT NULL,
  `timestamp` TIMESTAMP NULL,
  `seen` TINYINT(1) NULL,
  PRIMARY KEY (`messageID`),
  INDEX `fk_Message_Member1_idx` (`senderID` ASC),
  INDEX `fk_Message_Member2_idx` (`receivingID` ASC),
  CONSTRAINT `fk_Message_Member1`
    FOREIGN KEY (`senderID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Message_Member2`
    FOREIGN KEY (`receivingID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebid`.`Feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebid`.`Feedback` ;

CREATE TABLE IF NOT EXISTS `ebid`.`Feedback` (
  `transactionID` BIGINT NOT NULL,
  `sellerID` BIGINT NOT NULL,
  `buyerID` BIGINT NOT NULL,
  `itemID` BIGINT NOT NULL,
  `sellerComment` TEXT NULL,
  `buyerComment` TEXT NULL,
  `sellerRating` INT NULL,
  `buyerRating` INT NULL,
  PRIMARY KEY (`transactionID`),
  INDEX `fk_Feedback_Member1_idx` (`sellerID` ASC),
  INDEX `fk_Feedback_Member2_idx` (`buyerID` ASC),
  INDEX `fk_Feedback_Item1_idx` (`itemID` ASC),
  CONSTRAINT `fk_Feedback_Transaction1`
    FOREIGN KEY (`transactionID`)
    REFERENCES `ebid`.`Transaction` (`transactionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Member1`
    FOREIGN KEY (`sellerID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Member2`
    FOREIGN KEY (`buyerID`)
    REFERENCES `ebid`.`Member` (`memberID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Item1`
    FOREIGN KEY (`itemID`)
    REFERENCES `ebid`.`Item` (`itemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
