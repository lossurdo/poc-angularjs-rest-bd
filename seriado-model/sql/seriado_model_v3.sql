-- phpMyAdmin SQL Dump
-- version 4.3.12
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 22, 2016 at 05:02 PM
-- Server version: 5.5.46-0ubuntu0.14.04.2
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `seriado_model`
--

-- --------------------------------------------------------

--
-- Table structure for table `genero`
--

CREATE TABLE IF NOT EXISTS `genero` (
  `id` int(11) NOT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `dth_cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genero`
--

INSERT INTO `genero` (`id`, `genero`, `dth_cadastro`) VALUES
(45, 'Ação', '2016-01-22 18:09:26'),
(46, 'Animação', '2016-01-22 18:09:26'),
(47, 'Aventura', '2016-01-22 18:09:26'),
(48, 'Chanchada', '2016-01-22 18:09:26'),
(49, 'Cinema catástrofe', '2016-01-22 18:09:26'),
(50, 'Comédia', '2016-01-22 18:09:26'),
(51, 'Comédia romântica', '2016-01-22 18:09:26'),
(52, 'Comédia dramática', '2016-01-22 18:09:26'),
(53, 'Comédia de ação', '2016-01-22 18:09:26'),
(54, 'Cult', '2016-01-22 18:09:26'),
(55, 'Dança', '2016-01-22 18:09:27'),
(56, 'Documentários', '2016-01-22 18:09:27'),
(57, 'Drama', '2016-01-22 18:09:27'),
(58, 'Espionagem', '2016-01-22 18:09:27'),
(59, 'Erótico', '2016-01-22 18:09:27'),
(60, 'Fantasia', '2016-01-22 18:09:27'),
(61, 'Faroeste (ou western)', '2016-01-22 18:09:27'),
(62, 'Ficção científica', '2016-01-22 18:09:27'),
(63, 'Franchise/Séries', '2016-01-22 18:09:27'),
(64, 'Guerra', '2016-01-22 18:09:27'),
(65, 'Machinima', '2016-01-22 18:09:27'),
(66, 'Masala', '2016-01-22 18:09:27'),
(67, 'Musical', '2016-01-22 18:09:27'),
(68, 'Filme noir', '2016-01-22 18:09:27'),
(69, 'Policial', '2016-01-22 18:09:27'),
(70, 'Pornochanchada', '2016-01-22 18:09:27'),
(71, 'Pornográfico', '2016-01-22 18:09:27'),
(72, 'Romance', '2016-01-22 18:09:27'),
(73, 'Suspense', '2016-01-22 18:09:27'),
(74, 'Terror (ou horror)', '2016-01-22 18:09:27'),
(75, 'Trash', '2016-01-22 18:09:27');

-- --------------------------------------------------------

--
-- Table structure for table `seriado`
--

CREATE TABLE IF NOT EXISTS `seriado` (
  `id` int(11) NOT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `sumario` text NOT NULL,
  `ano` int(11) DEFAULT NULL,
  `url_poster` varchar(250) DEFAULT NULL,
  `url_trailer_youtube` varchar(250) DEFAULT NULL,
  `nota` double DEFAULT NULL,
  `criadores` varchar(250) DEFAULT NULL,
  `elenco` varchar(250) DEFAULT NULL,
  `finalizado` tinyint(1) NOT NULL DEFAULT '0',
  `dth_cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seriado`
--

INSERT INTO `seriado` (`id`, `titulo`, `sumario`, `ano`, `url_poster`, `url_trailer_youtube`, `nota`, `criadores`, `elenco`, `finalizado`, `dth_cadastro`) VALUES
(1, 'Game of Thrones', 'While a civil war brews between several noble families, in Westeros, the children of the former rulers of the land attempt to rise up to power. Meanwhile a forgotten race, bent on destruction, return after thousands of years in the North.', 2011, 'http://ia.media-imdb.com/images/M/MV5BMTYwOTEzMDMzMl5BMl5BanBnXkFtZTgwNzExODIzNzE@._V1_SX300.jpg', 'https://youtu.be/D7FO__nrwnU', 9.5, 'David Benioff, D.B. Weiss', 'Peter Dinklage, Lena Headey, Emilia Clarke, Kit Harington', 0, '2016-01-22 17:49:12'),
(2, 'Homeland', 'When Marine Nicolas Brody is hailed as a hero after he returns home from eight years of captivity in Iraq, intelligence officer Carrie Mathison is the only one who suspects that he may have been turned.', 2011, 'http://ia.media-imdb.com/images/M/MV5BMjE0NTc2MzM1OV5BMl5BanBnXkFtZTgwMDk2NjA2NjE@._V1_SX300.jpg', NULL, 8.4, 'Alex Gansa, Howard Gordon', 'Claire Danes, Mandy Patinkin, Rupert Friend, Damian Lewis', 0, '2016-01-22 17:49:12'),
(3, 'The Big Bang Theory', 'A woman who moves into an apartment across the hall from two brilliant but socially awkward physicists shows them how little they know about life outside of the laboratory.', 2007, 'http://ia.media-imdb.com/images/M/MV5BMjI1Mzc4MDUwNl5BMl5BanBnXkFtZTgwMDAzOTIxMjE@._V1_SX300.jpg', NULL, 8.4, 'Chuck Lorre, Bill Prady', 'Johnny Galecki, Jim Parsons, Kaley Cuoco, Simon Helberg', 0, '2016-01-22 17:49:12');

-- --------------------------------------------------------

--
-- Table structure for table `seriado_genero`
--

CREATE TABLE IF NOT EXISTS `seriado_genero` (
  `seriado_id` int(11) NOT NULL,
  `genero_id` int(11) NOT NULL,
  `dth_cadastro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seriado_genero`
--

INSERT INTO `seriado_genero` (`seriado_id`, `genero_id`, `dth_cadastro`) VALUES
(1, 47, '2016-01-22 18:33:36'),
(1, 57, '2016-01-22 18:33:36'),
(1, 60, '2016-01-22 18:33:36'),
(2, 57, '2016-01-22 19:00:16'),
(2, 73, '2016-01-22 19:00:16'),
(3, 50, '2016-01-22 19:01:59');

-- --------------------------------------------------------

--
-- Table structure for table `teste`
--

CREATE TABLE IF NOT EXISTS `teste` (
  `id` int(11) NOT NULL,
  `valor` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `genero_UNIQUE` (`genero`);

--
-- Indexes for table `seriado`
--
ALTER TABLE `seriado`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `titulo_UNIQUE` (`titulo`);

--
-- Indexes for table `seriado_genero`
--
ALTER TABLE `seriado_genero`
  ADD PRIMARY KEY (`seriado_id`,`genero_id`), ADD KEY `fk_seriado_has_genero_genero1_idx` (`genero_id`), ADD KEY `fk_seriado_has_genero_seriado_idx` (`seriado_id`);

--
-- Indexes for table `teste`
--
ALTER TABLE `teste`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genero`
--
ALTER TABLE `genero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=76;
--
-- AUTO_INCREMENT for table `seriado`
--
ALTER TABLE `seriado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `teste`
--
ALTER TABLE `teste`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `seriado_genero`
--
ALTER TABLE `seriado_genero`
ADD CONSTRAINT `fk_seriado_has_genero_genero1` FOREIGN KEY (`genero_id`) REFERENCES `genero` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_seriado_has_genero_seriado` FOREIGN KEY (`seriado_id`) REFERENCES `seriado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
