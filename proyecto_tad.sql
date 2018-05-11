-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2018 at 06:08 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `proyecto_tad`
--
CREATE DATABASE IF NOT EXISTS `localdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `localdb`;

-- --------------------------------------------------------

--
-- Table structure for table `army`
--

DROP TABLE IF EXISTS `army`;
CREATE TABLE `army` (
  `army_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `faction` varchar(50) NOT NULL,
  `strategy` varchar(200) NOT NULL,
  `player_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `army`
--

INSERT INTO `army` (`army_id`, `name`, `faction`, `strategy`, `player_id`) VALUES
(1, 'Ultramarines', 'IMPERIUM', 'Balanced', 1),
(2, 'Black Legion', 'CHAOS', 'Balanced', 1),
(3, 'Sautehk', 'XENOS', 'Balanced', 1),
(4, 'Khorne', 'CHAOS', 'Aggressive', 2);

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `game_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `winner_id` int(11) NOT NULL,
  `loser_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`game_id`, `date`, `winner_id`, `loser_id`) VALUES
(1, '2018-04-03', 1, 2),
(2, '2018-04-16', 2, 3),
(9, '2018-04-15', 1, 2),
(11, '2018-04-13', 2, 3),
(12, '2018-04-11', 1, 3),
(13, '2018-04-27', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
  `player_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(450) NOT NULL,
  `email` varchar(50) NOT NULL,
  `points` int(4) NOT NULL,
  `nickname` varchar(25) NOT NULL,
  `type` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`player_id`, `name`, `password`, `email`, `points`, `nickname`, `type`) VALUES
(1, 'qwweqwwe', 'qwweqwwe', 'qwweqwwe', 123, 'qweqwwe', 1),
(2, 'name', 'password', 'aaaa@gmail.com', 0, 'nickname', 1),
(3, 'admin', 'admin', 'admin@admin.com', 0, 'admin', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `army`
--
ALTER TABLE `army`
  ADD PRIMARY KEY (`army_id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `player_id` (`player_id`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`game_id`),
  ADD KEY `winner_id` (`winner_id`),
  ADD KEY `loser_id` (`loser_id`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`player_id`),
  ADD UNIQUE KEY `nickname` (`nickname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `army`
--
ALTER TABLE `army`
  MODIFY `army_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `player`
--
ALTER TABLE `player`
  MODIFY `player_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `army`
--
ALTER TABLE `army`
  ADD CONSTRAINT `army_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`);

--
-- Constraints for table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `FK_batuhu8ackerch37ddyixdj7f` FOREIGN KEY (`winner_id`) REFERENCES `army` (`army_id`),
  ADD CONSTRAINT `FK_sthd4y8eqshiuv3qtl9ybkjwl` FOREIGN KEY (`loser_id`) REFERENCES `army` (`army_id`),
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`winner_id`) REFERENCES `player` (`player_id`),
  ADD CONSTRAINT `game_ibfk_2` FOREIGN KEY (`loser_id`) REFERENCES `player` (`player_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
