# Host: localhost  (Version 5.5.5-10.1.21-MariaDB)
# Date: 2022-09-27 20:56:42
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "crud"
#

DROP TABLE IF EXISTS `crud`;
CREATE TABLE `crud` (
  `id_crud` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `alamat` text NOT NULL,
  PRIMARY KEY (`id_crud`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

#
# Data for table "crud"
#

INSERT INTO `crud` VALUES (15,'fanny brawijaya','L','kraksaan'),(19,'fian','L','paiton');
