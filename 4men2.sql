-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: 4men
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Aristino'),(2,'Routine'),(3,'Coolmate'),(4,'BLENTINO '),(5,'STORE'),(6,'Jockey'),(7,'Biluxury'),(8,'Polomanor'),(9,'Galvin Official'),(10,'Owen');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Áo sơ mi'),(2,'Áo polo'),(3,'Áo thun'),(4,'Áo khoác'),(5,'Áo len'),(6,'Quần tây'),(7,'Quần jeans'),(8,'Quần kaki'),(9,'Quần short'),(10,'Quần lót'),(11,'Thắt lưng'),(12,'Ví da'),(13,'Cà vạt'),(14,'Giày'),(15,'Dép nam');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `galery`
--

DROP TABLE IF EXISTS `galery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `galery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `thumbnail` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22cejnl41hauv9dx5lp0dwg62` (`product_id`),
  CONSTRAINT `FK22cejnl41hauv9dx5lp0dwg62` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `galery`
--

LOCK TABLES `galery` WRITE;
/*!40000 ALTER TABLE `galery` DISABLE KEYS */;
INSERT INTO `galery` VALUES (3,'anh1.jpg',4),(4,'anh2.jpg',4),(5,'anh3.jpg',5),(6,'anh4.jpg',6),(7,'anh4.jpg',4);
/*!40000 ALTER TABLE `galery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invalidated_token`
--

DROP TABLE IF EXISTS `invalidated_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `invalidated_token` (
  `id` varchar(255) NOT NULL,
  `expiry_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invalidated_token`
--

LOCK TABLES `invalidated_token` WRITE;
/*!40000 ALTER TABLE `invalidated_token` DISABLE KEYS */;
INSERT INTO `invalidated_token` VALUES ('1f834cef-7785-434c-b264-95fc576ecdd9','2024-06-12 05:41:15.000000'),('e7a1199d-00dd-4efb-bf89-4d7b6eb3ec18','2024-06-12 05:39:34.000000');
/*!40000 ALTER TABLE `invalidated_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oder`
--

DROP TABLE IF EXISTS `oder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `oder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_delivery` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state_oder` varchar(255) DEFAULT NULL,
  `total_money` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `oder_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oder`
--

LOCK TABLES `oder` WRITE;
/*!40000 ALTER TABLE `oder` DISABLE KEYS */;
INSERT INTO `oder` VALUES (3,'HCM','2024-06-03 00:00:00.000000','giang@gmail.com','Trần lê giang',5,'0958337247','Đang giao hàng',890000,11),(4,'HCM','2024-06-03 00:00:00.000000','giang@gmail.com','Trần lê giang',4,'0666666666','Đang giao hàng',55454454,15);
/*!40000 ALTER TABLE `oder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oder_detail`
--

DROP TABLE IF EXISTS `oder_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `oder_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discount` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `total_price` int(11) DEFAULT NULL,
  `oder_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `date_order` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnh6ty518khyber2rgjd5t2g4t` (`oder_id`),
  KEY `FKcch6rg73ev8of0xq5afs8x9gq` (`product_id`),
  CONSTRAINT `FKcch6rg73ev8of0xq5afs8x9gq` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKnh6ty518khyber2rgjd5t2g4t` FOREIGN KEY (`oder_id`) REFERENCES `oder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oder_detail`
--

LOCK TABLES `oder_detail` WRITE;
/*!40000 ALTER TABLE `oder_detail` DISABLE KEYS */;
INSERT INTO `oder_detail` VALUES (4,0,1,320000,320000,3,5,'2024-04-04'),(5,0,1,7000000,7000000,3,5,'2024-01-01'),(6,0,1,7000000,400000,4,5,'2024-04-04'),(7,0,1,7000000,2800000,4,5,'2024-05-05');
/*!40000 ALTER TABLE `oder_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `permission` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES ('APPROVE_A_POST','Approve a post'),('CREATE_DATA','Create data'),('UPDATE_DATA','Update data');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text,
  `discount` float DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `quantity_in_stock` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'2024-05-31 13:32:20.589389','Đầu khóa thắt lưng: Khóa đầu kim\nKích thước: Freesize\nMàu: Đen / Nâu / Trắng / Xám / Xanh Đen ',0.1,'ÁO THUN PHỐI MÀU AT060 MÀU TRẮNG',275000,36,NULL,10,3),(4,'2024-05-31 16:04:58.531273','Thông tin sản phẩm:\n- Mã sản phẩm: AT148\n- Chất liệu: Thun Cá Sấu (97% cotton và 3% Spandex)\n- Họa tiết: Thêu\n- Form: Regular\n- Màu: Trắng / Xanh Đen',0,'ÁO THUN REGULAR IN AT085 MÀU ĐEN',295000,15,NULL,8,3),(5,'2024-05-31 19:17:21.047602','mô tả 123',0.15,'ÁO THUN REGULAR RACING AT084 MÀU BE',390000,34,NULL,7,3),(6,'2024-05-31 19:39:54.371179','Thông tin sản phẩm:\n- Mã sản phẩm: QS056\n- Chất liệu: Linen (96% linen và 4% spandex)\n- Họa tiết: Trơn\n- Form: Straight\n- Màu: Kem / Xanh Đen',0.1,'ÁO THUN REGULAR TÚI AT105 MÀU ĐEN',395000,23,NULL,6,8);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ADMIN','Admin role'),('USER','User role');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role_permissions` (
  `role_name` varchar(255) NOT NULL,
  `permissions_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_name`,`permissions_name`),
  KEY `FKf5aljih4mxtdgalvr7xvngfn1` (`permissions_name`),
  CONSTRAINT `FKcppvu8fk24eqqn6q4hws7ajux` FOREIGN KEY (`role_name`) REFERENCES `role` (`name`),
  CONSTRAINT `FKf5aljih4mxtdgalvr7xvngfn1` FOREIGN KEY (`permissions_name`) REFERENCES `permission` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES ('ADMIN','APPROVE_A_POST'),('ADMIN','CREATE_DATA'),('USER','CREATE_DATA'),('ADMIN','UPDATE_DATA');
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'Nam Định','2002-11-09','2024-05-30 02:15:13.814740','tandat@gmail.com','tấn đạt 123',_binary '','avatar nam 6.png','$2a$10$D3Xa38.SApdcuicLKUmCL.P0WVrLyWLUGbJ/rtYECclmGYgeZ3BjS','0394857275','Đang hoạt động',NULL,'tandat123',NULL,NULL),(15,'Gia Lai, Chư Prong','2005-12-05','2024-05-29 03:55:43.165984','haiphan@gmail.com','Phan văn hải ',_binary '','avatar nam 3.png','haiphan123890','0384958271','Không hoạt động',NULL,'haiphan123',NULL,NULL),(18,'Vĩnh Phúc','2003-12-23','2024-05-30 02:16:20.312716','da@gmail.com','Phan đã',_binary '','avatar nam 4.png','phanda789','0384957383','Đang hoạt động','2024-06-07 17:12:39.510714','phanda123',NULL,NULL),(30,'Ninh Bình','2005-02-09','2024-05-30 15:59:28.427258','tuyet@gmail.com','Nguyễn Thị Tuyết',_binary '\0','avatar nữ 6.png','123456','0395758275','Không hoạt động',NULL,'tuyetnguyen',NULL,NULL),(37,'Bình Thuận','2005-03-09','2024-05-30 15:54:05.321742','ductho@gmail.com','Lê đức thọ',_binary '','avatar nam 7.png','123456','0948372837','Đang hoạt động',NULL,'ductho',NULL,NULL),(39,'',NULL,'2024-05-31 18:07:41.341392','dfhfg@gmail.com','',_binary '\0',NULL,'dfgdfshdf','','Đang hoạt động',NULL,'sdgfdgdfgds',NULL,NULL),(40,'',NULL,'2024-06-07 01:54:12.771345','nghia@gmail.com','',_binary '',NULL,'$2a$10$OOg36yYhQyMxNPWELa2WvuGZwTgWoXDXUvzqdLKkq2ZgSf/8xkPHy','','Không hoạt động',NULL,'nghia1234',NULL,NULL),(41,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'$2a$10$v63Q5zXv4eW5qPYJpKkZNO4SVz4zTcLstoEdua.tHj4.RN2SEMJNm',NULL,NULL,NULL,'admin',NULL,NULL),(42,'TPHCM',NULL,NULL,'','',_binary '\0',NULL,'$2a$10$MBpV0Z85ok0m5JzIN9zGl./4sFlqbqwuEz.bG254HireaW79QviRq','0948372859',NULL,NULL,'huunghia',NULL,NULL),(43,'','2006-07-13',NULL,'nghia@gmail.com','huunghiaaa',_binary '\0','avatar nam 4.png','$2a$10$yIxcBTUwHEfdZV0gtat6oexPBnDPZCcLNrPZozsKZUik88zJ9reey','','Đang hoạt động',NULL,'user111',NULL,NULL),(44,'',NULL,NULL,'user6@example.com','user1',_binary '','anh2.jpg','$2a$10$FmD2P53.fqTxUmGvsr5o0Ov4e2oMTGboOnzOrHYF/7C6uIzAX1wg2','','Đang hoạt động',NULL,'john_doe',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `roles_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_name`),
  KEY `FK6pmbiap985ue1c0qjic44pxlc` (`roles_name`),
  CONSTRAINT `FK6pmbiap985ue1c0qjic44pxlc` FOREIGN KEY (`roles_name`) REFERENCES `role` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (41,'ADMIN'),(11,'USER'),(42,'USER'),(43,'USER'),(44,'USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-13 21:56:24
