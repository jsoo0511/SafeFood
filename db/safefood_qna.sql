-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: safefood
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qna` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(30) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `regdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `contents` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (2,'ssafy','stagsdasd','2019-11-20 02:13:35','sasdasng'),(3,'ssafy','TitleTitle','2019-11-20 02:35:28','I\'m so curious'),(6,NULL,'ㅁㄴㅇㄻㄴㅇㄹ','2019-11-20 04:48:27','ㅁㅇㄴㄻㄴㅇㄻㄴㅇㄹ'),(7,'문지현','박동준','2019-11-20 04:51:16','돼'),(8,'answlgus','오늘의 미세먼지어떤가요','2019-11-20 05:27:16','(세종=연합뉴스) 김수현 기자 = 국내 초미세먼지(PM-2.5) 중 국내 영향으로 발생한 것은 절반가량이고 32%는 중국에서 비롯한 것이라는 한국, 중국, 일본 3국의 첫 공동 연구 결과가 나왔다.\n\n연평균 농도를 기준으로 한 이번 연구 결과에서 밝혀지지 않았지만, 겨울철 미세먼지 고농도 시기에는 국외 영향이 커지며 국내 미세먼지 중 중국발 요인이 70%에 이르는 것으로도 파악됐다.g\n\n환경부 소속 국립환경과학원은 20일 한·중·일 3국의 연구 결과를 토대로 정책 결정자를 위한 \'동북아 장거리 이동 대기오염물질 국제 공동연구(LTP)\' 요약 보고서를 발간했다.\n\n보고서에 따르면 2017년 대기 질 모델 기법을 이용해 한국 3개 도시(서울, 대전, 부산)의 국내외 초미세먼지 발생 요인을 분석한 결과, 국내에서 비롯한 요인(자체 기여율)이 51%, 국외 요인이 49%로 나타났다.'),(9,'dd','dd','2019-11-20 06:36:41','ss'),(10,'dd','dd','2019-11-20 06:36:44','ss'),(12,'zfgsaf','sdafasdf','2019-11-20 07:49:52','sadfasdmlkfnsadf');
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-22 17:39:23
