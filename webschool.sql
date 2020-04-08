-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Фев 18 2020 г., 10:21
-- Версия сервера: 10.1.33-MariaDB
-- Версия PHP: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `myschool`
--
CREATE DATABASE IF NOT EXISTS `myschool` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `myschool`;

-- --------------------------------------------------------

--
-- Структура таблицы `grade`
--

CREATE TABLE `grade` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TEXT` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `grade`
--

INSERT INTO `grade` (`ID`, `NAME`, `TEXT`) VALUES
(1, '1', 'плохо'),
(2, '2', 'неудовлетворительно'),
(3, '3', 'удовлетворительно'),
(4, '4', 'хорошо'),
(5, '5', 'отлично');

-- --------------------------------------------------------

--
-- Структура таблицы `history`
--

CREATE TABLE `history` (
  `ID` bigint(20) NOT NULL,
  `RECORD` datetime DEFAULT NULL,
  `GRADE_ID` bigint(20) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL,
  `SUBJECT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `history`
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
-- Структура таблицы `person`
--

CREATE TABLE `person` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `person`
--

INSERT INTO `person` (`ID`, `NAME`) VALUES
(1, 'Ivan Ivanoff'),
(2, 'Петр Васечкин'),
(3, 'Вася Пупкин'),
(4, 'Вова Петров');

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `ID` bigint(20) NOT NULL,
  `ROLE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`ID`, `ROLE`) VALUES
(1, 'ADMIN'),
(2, 'MANAGER'),
(3, 'USER');

-- --------------------------------------------------------

--
-- Структура таблицы `subject`
--

CREATE TABLE `subject` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TEACHER` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `subject`
--

INSERT INTO `subject` (`ID`, `NAME`, `TEACHER`) VALUES
(1, 'Физика', 'Ньютон'),
(2, 'Химия', 'Менделеев'),
(3, 'Литература', 'Пушкин'),
(4, 'Физкультура', 'Шварценеггер');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `SALTS` varchar(255) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`ID`, `LOGIN`, `PASSWORD`, `SALTS`, `PERSON_ID`) VALUES
(1, 'admin', '355f7ba002b5cb80858d31cdec700d71aa17045c392fcae12ecb75c2e2715b65', '4430b3a52db235f991c8d5e4f2d707b5', 1),
(2, 'petr', '4b5453f4371e53362b1b70ae433b6f814d134deecf1cff187315e6a308ad356', '8f9c1c68058c13da3f04dae4c6a87c25', 2),
(3, 'vasja', 'ee4ea2c15010c36833652426b610ce62325f18c7437a488bc0b9749f33e1e80b', '511becf309556333102132a64ec65d94', 3),
(4, 'vova', '4c2f9c103eec33be117dc409013b11bb1e4d09207fc78e77309506b3efdad0ad', '44450dd42b4a07ee3562a8eb1c08410e', 4);

-- --------------------------------------------------------

--
-- Структура таблицы `userroles`
--

CREATE TABLE `userroles` (
  `ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `userroles`
--

INSERT INTO `userroles` (`ID`, `ROLE_ID`, `USER_ID`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_HISTORY_GRADE_ID` (`GRADE_ID`),
  ADD KEY `FK_HISTORY_PERSON_ID` (`PERSON_ID`),
  ADD KEY `FK_HISTORY_SUBJECT_ID` (`SUBJECT_ID`);

--
-- Индексы таблицы `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ROLE` (`ROLE`);

--
-- Индексы таблицы `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `LOGIN` (`LOGIN`),
  ADD KEY `FK_USER_PERSON_ID` (`PERSON_ID`);

--
-- Индексы таблицы `userroles`
--
ALTER TABLE `userroles`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_USERROLES_USER_ID` (`USER_ID`),
  ADD KEY `FK_USERROLES_ROLE_ID` (`ROLE_ID`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `grade`
--
ALTER TABLE `grade`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `history`
--
ALTER TABLE `history`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблицы `person`
--
ALTER TABLE `person`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `subject`
--
ALTER TABLE `subject`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `userroles`
--
ALTER TABLE `userroles`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FK_HISTORY_GRADE_ID` FOREIGN KEY (`GRADE_ID`) REFERENCES `grade` (`ID`),
  ADD CONSTRAINT `FK_HISTORY_PERSON_ID` FOREIGN KEY (`PERSON_ID`) REFERENCES `person` (`ID`),
  ADD CONSTRAINT `FK_HISTORY_SUBJECT_ID` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`ID`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_USER_PERSON_ID` FOREIGN KEY (`PERSON_ID`) REFERENCES `person` (`ID`);

--
-- Ограничения внешнего ключа таблицы `userroles`
--
ALTER TABLE `userroles`
  ADD CONSTRAINT `FK_USERROLES_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`),
  ADD CONSTRAINT `FK_USERROLES_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
