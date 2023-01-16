# Host: localhost  (Version 5.5.5-10.1.21-MariaDB)
# Date: 2023-01-15 12:02:24
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
  `nominal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_crud`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

#
# Data for table "crud"
#

INSERT INTO `crud` VALUES (15,'fanny brawijaya','L','10000',1212),(19,'fian','L','8000',1212),(20,'vina','P','12500',1212),(36,'fina','L','2000',NULL);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` int(8) DEFAULT NULL,
  `flag` int(4) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'fanny',123,1);
