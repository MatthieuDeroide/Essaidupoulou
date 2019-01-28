-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 09 déc. 2018 à 22:56
-- Version du serveur :  10.1.36-MariaDB
-- Version de PHP :  7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projets7`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `mdp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_admin`, `nom`, `prenom`, `mail`, `mdp`) VALUES
(1, 'test1', 'test2', 'admin@test.fr', '$argon2i$v=19$m=65536,t=5,p=1$w24TUONSbIM3WP0wdyH0cvnezkcrxP089Sv9lGFQ3wCFjWP2gmJsYiRjDyPBPk0nwtciexua7hbNvukpfvkG9yV9J53G6ZhvSPz1fmK2F7PY/aV7m1b3scZCTJe4rGi6E2TMwE/h1b3wdNxwND/iq5HD7VFd4xkz+U5k5XNsgLA$e4/2gKBkniG5ebJvhz2+GSpMBYbtG15GiCHzS+QXgy5xS0T9q+fDoKic+lxBMCwbAWcCqK3U+gx4JI1LSUcVlxuGnK40OmDqQZnTIPihTV9omeFD3LAI3Ob+ml7/VpLcVKJUC2Soh5MKM6EehtGQAlm9AtDpRJPjLUYcbzren7c');

-- --------------------------------------------------------

--
-- Structure de la table `aideoperatoire`
--

CREATE TABLE `aideoperatoire` (
  `id` int(100) NOT NULL,
  `civilite` text NOT NULL,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `dateNaissance` date NOT NULL,
  `pays` text NOT NULL,
  `ville` text NOT NULL,
  `codePostal` int(5) NOT NULL,
  `adresse` text NOT NULL,
  `posteOccupe` text NOT NULL,
  `mail` varchar(255) NOT NULL,
  `numeroTel` text NOT NULL,
  `mdp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `aideoperatoire`
--

INSERT INTO `aideoperatoire` (`id`, `civilite`, `nom`, `prenom`, `dateNaissance`, `pays`, `ville`, `codePostal`, `adresse`, `posteOccupe`, `mail`, `numeroTel`, `mdp`) VALUES
(1, 'test1', 'test2', 'test3', '2018-11-09', 'testpays', 'testville', 59, 'testadresse', 'testposte', 'aideop@test.fr', 'testnumeroTel', '$argon2i$v=19$m=65536,t=5,p=1$YAxkl+/fKn+wCaORCoWpjTX0bT4Sk8FFHSXIx3x3K0x7HxHn7YUuYO96UmxQ2qSL43TiXSkSAlFOyVZbrd7GXLSVClZfPRfaJuhBqfShF4Q3/mrYla9TlOSmt1ed+OkksyjY152Z/gRhiROg/u/XQwzJJQ+MuOlGZOJtPMmQ3ak$T9G3Nvr425NJvL5nsSUj19A9NBZA5QQdgKCoC5jGHZo8443xWTHFMPd+EDZQiWcntsTd0Nr5sJMMeYxnEhVASxV9hfU9nkAejj5Gvaf97MyARyasl7ec8iNbWvsKRdha02oTzyeTn5DiOqJiTyfu4EMJuuMeKa2kv3lsroeE8cw');

-- --------------------------------------------------------

--
-- Structure de la table `docteur`
--

CREATE TABLE `docteur` (
  `id` int(100) NOT NULL,
  `professeur` tinyint(1) NOT NULL DEFAULT '0',
  `civilite` varchar(255) NOT NULL,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `dateNaissance` date NOT NULL,
  `pays` text NOT NULL,
  `ville` text NOT NULL,
  `codePostal` int(5) NOT NULL,
  `adresse` text NOT NULL,
  `posteOccupe` text NOT NULL,
  `mail` text NOT NULL,
  `numeroTel` text NOT NULL,
  `mdp` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `docteur`
--

INSERT INTO `docteur` (`id`, `professeur`, `civilite`, `nom`, `prenom`, `dateNaissance`, `pays`, `ville`, `codePostal`, `adresse`, `posteOccupe`, `mail`, `numeroTel`, `mdp`) VALUES
(1, 0, 'test1', 'test2', 'test3', '2018-11-09', 'testpays', 'testville', 59, 'testadresse', 'testposte', 'docteur@test.fr', 'testnumeroTel', '$argon2i$v=19$m=65536,t=5,p=1$AtIIgQzj+4N4UbQXmSE97TXNyMvf44ad2+UI4hZApK8Hwcko5LOix/Rp1W7hDXqx6iDeVr25JrA3jieKSuzWvt6yGHPDbeR/bM4XM3uz0s+PGI3Q4PDf6AJKsPWIma9mGna03hKKJ6rkIM/CyHjMrUw4yJqQUGwDITMFJbqJQug$z120AvVPR1H+k6JE7uXKA7kVRh9cFAoC3o5l4KTkyvXJiVbT+gD7T+lxRre/zomkNh2SCSzBNrp0RfB2qB2rMLInSh+EmWrajYNrB7Ju2WyQPmOEMnh5/51OGrWG2PL1XtyKt/KL5V26t2YOEehwlMfSysONlzJ/Rrr8bI4S8ZI');

-- --------------------------------------------------------

--
-- Structure de la table `offres`
--

CREATE TABLE `offres` (
  `id` int(100) NOT NULL,
  `titre` text NOT NULL,
  `description` text NOT NULL,
  `datedebut` date NOT NULL,
  `datefin` date NOT NULL,
  `professionRecherchee` text NOT NULL,
  `adresse` text NOT NULL,
  `etablissement` text NOT NULL,
  `codePostal` int(5) NOT NULL,
  `auteur` int(11) DEFAULT NULL,
  `isaideop` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `offres`
--

INSERT INTO `offres` (`id`, `titre`, `description`, `datedebut`, `datefin`, `professionRecherchee`, `adresse`, `etablissement`, `codePostal`, `auteur`, `isaideop`) VALUES
(1, 'test1', 'test2', '2018-11-08', '2018-11-09', 'testprofession', 'testadresse', 'testetablissement', 59, 1, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Index pour la table `aideoperatoire`
--
ALTER TABLE `aideoperatoire`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `docteur`
--
ALTER TABLE `docteur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `offres`
--
ALTER TABLE `offres`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `aideoperatoire`
--
ALTER TABLE `aideoperatoire`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `docteur`
--
ALTER TABLE `docteur`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `offres`
--
ALTER TABLE `offres`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
