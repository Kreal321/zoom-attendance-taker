DROP DATABASE IF EXISTS `attendance`;

CREATE DATABASE `attendance`;
USE `attendance`;


DROP TABLE IF EXISTS `participant`;
CREATE TABLE `participant` (
  `participant_uuid` varchar(25) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `preferred_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`participant_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `meeting_uuid` varchar(30) NOT NULL,
  `meeting_id` varchar(10) DEFAULT NULL,
  `host_id` varchar(45) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`meeting_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `attendance_id` int NOT NULL AUTO_INCREMENT,
  `meeting_uuid` varchar(30) DEFAULT NULL,
  `participant_uuid` varchar(25) DEFAULT NULL,
  `duration` int DEFAULT 0,
  `is_final` bit(1) DEFAULT 0,
  PRIMARY KEY (`attendance_id`),
  KEY `meetingUuid_idx` (`meeting_uuid`),
  KEY `participantId_idx` (`participant_uuid`),
  CONSTRAINT `meeting_uuid` FOREIGN KEY (`meeting_uuid`) REFERENCES `meeting` (`meeting_uuid`),
  CONSTRAINT `participant_id` FOREIGN KEY (`participant_uuid`) REFERENCES `participant` (`participant_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `attendance_id` int DEFAULT NULL,
  `event_time` timestamp NULL DEFAULT NULL,
  `event_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `attendanceId_idx` (`attendance_id`),
  CONSTRAINT `attendance_id` FOREIGN KEY (`attendance_id`) REFERENCES `attendance` (`attendance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

