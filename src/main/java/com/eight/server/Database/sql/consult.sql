DROP DATABASE IF EXISTS consult;

CREATE DATABASE IF NOT EXISTS consult DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE consult;

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `db_student`;
CREATE TABLE `db_student` (
  `sno` char(15) NOT NULL COMMENT '来访者编号',
  `sname` char(20) NOT NULL COMMENT '姓名',
  `sdept` char(20) NOT NULL COMMENT '院系',
  `sphone` char(15) NOT NULL COMMENT '联系方式',
  `sclass` enum('普通','严重','障碍','高危') DEFAULT NULL COMMENT '等级',
  `scon` char(20) NOT NULL COMMENT '紧急联系人',
  `sconph` char(15) NOT NULL COMMENT '紧急联系人联系方式',
  `spwd` char(20) NOT NULL COMMENT '密码',
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '来访者基本表';

DROP TABLE IF EXISTS `db_consultant`;
CREATE TABLE `db_consultant` (
  `cno` char(15) NOT NULL COMMENT '咨询师编号',
  `cname` char(20) NOT NULL COMMENT '姓名',
  `cphone` char(15) NOT NULL COMMENT '联系方式',
  `cclass` enum('一级咨询师','二级咨询师','三级咨询师') DEFAULT NULL COMMENT '等级',
  `cpwd` char(20) NOT NULL COMMENT '密码',
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '咨询师基本表';

DROP TABLE IF EXISTS `db_scale`;
CREATE TABLE `db_scale` (
  `scno` char(15) NOT NULL COMMENT '量表编号',
  `scname` char(20) NOT NULL COMMENT '名称',
  `scdescribe` char(120) NOT NULL COMMENT '描述',
  `sctype` char(10) NOT NULL COMMENT '类别',
  PRIMARY KEY (`scno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '量表基本表';

DROP TABLE IF EXISTS `db_instructor`;
CREATE TABLE `db_instructor` (
  `ino` char(15) NOT NULL COMMENT '辅导员编号',
  `iname` char(20) NOT NULL COMMENT '姓名',
  `iphone` char(15) NOT NULL COMMENT '联系方式',
  `idept` char(20) NOT NULL COMMENT '院系',
  `ipwd` char(20) NOT NULL COMMENT '密码',
  PRIMARY KEY (`ino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '辅导员基本表';

DROP TABLE IF EXISTS `db_test`;
CREATE TABLE `db_test` (
  `tno` char(15) NOT NULL COMMENT '测试编号',
  `sno` char(15) NOT NULL COMMENT '来访者编号',
  `cno` char(15) DEFAULT NULL COMMENT '咨询师编号',
  `scno` char(15) NOT NULL COMMENT '量表编号',
  `tscore` int NOT NULL COMMENT '测试分数',
  `tresult` text NOT NULL COMMENT '测试结果',
  `ttime` datetime DEFAULT NULL COMMENT '测试时间',
  PRIMARY KEY (`tno`),
  KEY `sno` (`sno`),
  KEY `cno` (`cno`),
  KEY `scno` (`scno`),
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `db_student` (`sno`),
  CONSTRAINT `test_ibfk_2` FOREIGN KEY (`cno`) REFERENCES `db_consultant` (`cno`),
  CONSTRAINT `test_ibfk_3` FOREIGN KEY (`scno`) REFERENCES `db_scale` (`scno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '测试基本表';

DROP TABLE IF EXISTS `db_examine`;
CREATE TABLE `db_examine` (
  `eno` char(15) NOT NULL COMMENT '月排查表编号',
  `sno` char(15) NOT NULL COMMENT '来访者编号',
  `cno` char(15) NOT NULL COMMENT '咨询师编号',
  `ino` char(15) NOT NULL COMMENT '辅导员编号',
  `edept` char(20) NOT NULL COMMENT '院系',
  `etime` datetime DEFAULT NULL COMMENT '排查时间',
  `eresult` text NOT NULL COMMENT '排查结果',
  `feedbacktime` datetime DEFAULT NULL COMMENT '咨询师反馈时间',
  `feedbackresult` text NOT NULL COMMENT '咨询师反馈结果',
  PRIMARY KEY (`eno`),
  KEY `sno` (`sno`),
  KEY `cno` (`cno`),
  KEY `ino` (`ino`),
  CONSTRAINT `examine_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `db_student` (`sno`),
  CONSTRAINT `examine_ibfk_2` FOREIGN KEY (`cno`) REFERENCES `db_consultant` (`cno`),
  CONSTRAINT `examine_ibfk_3` FOREIGN KEY (`ino`) REFERENCES `db_instructor` (`ino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '月排查表基本表';

DROP TABLE IF EXISTS `db_consult`;
CREATE TABLE `db_consult` (
  `recordno` char(15) NOT NULL COMMENT '咨询记录编号',
  `sno` char(15) NOT NULL COMMENT '来访者编号',
  `cno` char(15) NOT NULL COMMENT '咨询师编号',
  `consulttime` datetime DEFAULT NULL COMMENT '咨询时间',
  `consultresult` text NOT NULL COMMENT '咨询结果',
  PRIMARY KEY (`recordno`),
  KEY `sno` (`sno`),
  KEY `cno` (`cno`),
  CONSTRAINT `consult_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `db_student` (`sno`),
  CONSTRAINT `consult_ibfk_2` FOREIGN KEY (`cno`) REFERENCES `db_consultant` (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '咨询基本表';