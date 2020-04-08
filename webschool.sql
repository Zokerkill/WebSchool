-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2020 at 02:20 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webschool`
--

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TEXT` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`ID`, `NAME`, `TEXT`) VALUES
(1, '1', 'плохо'),
(2, '2', 'неудовлетворительно'),
(3, '3', 'удовлетворительно'),
(4, '4', 'хорошо'),
(5, '5', 'отлично');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `ID` bigint(20) NOT NULL,
  `RECORD` datetime DEFAULT NULL,
  `GRADE_ID` bigint(20) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL,
  `SUBJECT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`ID`, `RECORD`, `GRADE_ID`, `PERSON_ID`, `SUBJECT_ID`) VALUES
(1, '2020-02-18 09:13:30', 2, 2, 1),
(2, '2020-02-18 09:13:37', 4, 3, 1),
(3, '2020-02-18 09:13:45', 3, 4, 1),
(4, '2020-02-18 09:13:57', 2, 2, 2),
(5, '2020-02-18 09:14:05', 4, 3, 2),
(6, '2020-02-18 09:14:16', 5, 4, 2),
(7, '2020-02-18 09:14:23', 3, 2, 3),
(8, '2020-02-18 09:14:31', 4, 3, 3),
(9, '2020-02-18 09:14:39', 2, 4, 3),
(10, '2020-02-18 09:14:49', 4, 2, 4),
(11, '2020-02-18 09:15:00', 5, 3, 4),
(12, '2020-02-18 09:15:11', 1, 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`ID`, `NAME`) VALUES
(1, 'Ivan Ivanoff'),
(2, 'Петр Васечкин'),
(3, 'Вася Пупкин'),
(4, 'Вова Петров');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `ID` bigint(20) NOT NULL,
  `ROLE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`ID`, `ROLE`) VALUES
(1, 'ADMIN'),
(2, 'MANAGER'),
(3, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TEACHER` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`ID`, `NAME`, `TEACHER`) VALUES
(1, 'Физика', 'Ньютон'),
(2, 'Химия', 'Менделеев'),
(3, 'Литература', 'Пушкин'),
(4, 'Физкультура', 'Шварценеггер');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `SALTS` varchar(255) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `LOGIN`, `PASSWORD`, `SALTS`, `PERSON_ID`) VALUES
(1, 'admin', '355f7ba002b5cb80858d31cdec700d71aa17045c392fcae12ecb75c2e2715b65', '4430b3a52db235f991c8d5e4f2d707b5', 1),
(2, 'petr', '4b5453f4371e53362b1b70ae433b6f814d134deecf1cff187315e6a308ad356', '8f9c1c68058c13da3f04dae4c6a87c25', 2),
(3, 'vasja', 'ee4ea2c15010c36833652426b610ce62325f18c7437a488bc0b9749f33e1e80b', '511becf309556333102132a64ec65d94', 3),
(4, 'vova', '4c2f9c103eec33be117dc409013b11bb1e4d09207fc78e77309506b3efdad0ad', '44450dd42b4a07ee3562a8eb1c08410e', 4);

-- --------------------------------------------------------

--
-- Table structure for table `userroles`
--

CREATE TABLE `userroles` (
  `ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `userroles`
--

INSERT INTO `userroles` (`ID`, `ROLE_ID`, `USER_ID`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_HISTORY_GRADE_ID` (`GRADE_ID`),
  ADD KEY `FK_HISTORY_PERSON_ID` (`PERSON_ID`),
  ADD KEY `FK_HISTORY_SUBJECT_ID` (`SUBJECT_ID`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ROLE` (`ROLE`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `LOGIN` (`LOGIN`),
  ADD KEY `FK_USER_PERSON_ID` (`PERSON_ID`);

--
-- Indexes for table `userroles`
--
ALTER TABLE `userroles`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_USERROLES_USER_ID` (`USER_ID`),
  ADD KEY `FK_USERROLES_ROLE_ID` (`ROLE_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `grade`
--
ALTER TABLE `grade`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `userroles`
--
ALTER TABLE `userroles`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK_HISTORY_GRADE_ID` FOREIGN KEY (`GRADE_ID`) REFERENCES `grade` (`ID`),
  ADD CONSTRAINT `FK_HISTORY_PERSON_ID` FOREIGN KEY (`PERSON_ID`) REFERENCES `person` (`ID`),
  ADD CONSTRAINT `FK_HISTORY_SUBJECT_ID` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`ID`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_USER_PERSON_ID` FOREIGN KEY (`PERSON_ID`) REFERENCES `person` (`ID`);

--
-- Constraints for table `userroles`
--
ALTER TABLE `userroles`
  ADD CONSTRAINT `FK_USERROLES_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`),
  ADD CONSTRAINT `FK_USERROLES_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
