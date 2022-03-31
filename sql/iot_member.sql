-- --------------------------------------------------------
-- 호스트:                          10.210.150.22
-- 서버 버전:                        10.3.34-MariaDB-0ubuntu0.20.04.1 - Ubuntu 20.04
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 db01.iot_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `iot_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(128) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `passwd` varchar(200) DEFAULT NULL,
  `usrname` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `remember` int(11) DEFAULT NULL,
  `usrgrant` varchar(50) DEFAULT NULL,
  `locked` int(11) DEFAULT NULL,
  `regidate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
