DROP DATABASE IF EXISTS `attendance`;

CREATE DATABASE `attendance`;
USE `attendance`;


DROP TABLE IF EXISTS `participant`;
CREATE TABLE `participant` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `participant_uuid` varchar(63) NOT NULL UNIQUE,
  `email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `m_id` int NOT NULL AUTO_INCREMENT,
  `meeting_uuid` varchar(63) NOT NULL UNIQUE,
  `meeting_id` varchar(63) NOT NULL,
  `host_id` varchar(63) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `type` int NOT NULL,
  `start_time` long NOT NULL,
  `end_time` long DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT 0,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `a_id` int NOT NULL AUTO_INCREMENT,
  `m_id` int NOT NULL,
  `p_id` int NOT NULL,
  `duration` int DEFAULT 0,
  `is_final` bit(1) DEFAULT 0,
  PRIMARY KEY (`a_id`),
  CONSTRAINT `fk_meeting` FOREIGN KEY (`m_id`) REFERENCES `meeting` (`m_id`),
  CONSTRAINT `fk_participant` FOREIGN KEY (`p_id`) REFERENCES `participant` (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `e_id` int NOT NULL AUTO_INCREMENT,
  `a_id` int NOT NULL,
  `event_time` long NOT NULL,
  `event_name` varchar(63) NOT NULL,
  PRIMARY KEY (`e_id`),
  CONSTRAINT `fk_attendance` FOREIGN KEY (`a_id`) REFERENCES `attendance` (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

