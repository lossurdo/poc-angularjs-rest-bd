-- phpMyAdmin SQL Dump
-- version 4.3.12
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 30, 2015 at 09:02 AM
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
  `genero` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genero`
--

INSERT INTO `genero` (`id`, `genero`) VALUES
(2, 'Aventura'),
(1, 'Ficção');

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
  `finalizado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `seriado_genero`
--

CREATE TABLE IF NOT EXISTS `seriado_genero` (
  `seriado_id` int(11) NOT NULL,
  `genero_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `teste`
--

CREATE TABLE IF NOT EXISTS `teste` (
  `id` int(11) NOT NULL,
  `valor` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `seriado`
--
ALTER TABLE `seriado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `teste`
--
ALTER TABLE `teste`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `seriado_genero`
--
ALTER TABLE `seriado_genero`
ADD CONSTRAINT `fk_seriado_has_genero_seriado` FOREIGN KEY (`seriado_id`) REFERENCES `seriado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_seriado_has_genero_genero1` FOREIGN KEY (`genero_id`) REFERENCES `genero` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
