-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-04-2018 a las 01:27:40
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_tad`
--
CREATE DATABASE IF NOT EXISTS `proyecto_tad` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proyecto_tad`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `army`
--

CREATE TABLE `army` (
  `army_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `faction` varchar(50) NOT NULL,
  `strategy` varchar(200) NOT NULL,
  `player_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `game`
--

CREATE TABLE `game` (
  `game_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `winner_id` int(11) NOT NULL,
  `loser_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `player`
--

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
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `army`
--
ALTER TABLE `army`
  ADD PRIMARY KEY (`army_id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `player_id` (`player_id`);

--
-- Indices de la tabla `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`game_id`),
  ADD KEY `winner_id` (`winner_id`),
  ADD KEY `loser_id` (`loser_id`);

--
-- Indices de la tabla `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`player_id`),
  ADD UNIQUE KEY `nickname` (`nickname`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `army`
--
ALTER TABLE `army`
  MODIFY `army_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `game`
--
ALTER TABLE `game`
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `player`
--
ALTER TABLE `player`
  MODIFY `player_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `army`
--
ALTER TABLE `army`
  ADD CONSTRAINT `army_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`);

--
-- Filtros para la tabla `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`winner_id`) REFERENCES `player` (`player_id`),
  ADD CONSTRAINT `game_ibfk_2` FOREIGN KEY (`loser_id`) REFERENCES `player` (`player_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
