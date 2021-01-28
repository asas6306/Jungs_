package BoardUseEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EnterDriver {
	String driver = "com.mysql.cj.jdbc.Driver";
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
//	String user = "sbsst";
//	String password = "sbs123414";
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
	String user = "root";
	String password = null;
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	public Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

//	sql 실습용 초기 명령어
//	CREATE DATABASE board;
//
//	USE Board;
//
//	CREATE TABLE article(
//	id INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//	title CHAR(100) NOT NULL,
//	`body` TEXT  NOT NULL,
//	userid INT(5) NOT NULL,
//	`date` CHAR(30) DEFAULT NOW(),
//	`like` INT(5) DEFAULT 0,
//	hit INT(5) DEFAULT 0
//	);
//	CREATE TABLE `member`(
//	userid INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//	ID CHAR(30) NOT NULL,
//	PW CHAR(30) NOT NULL,
//	nickname CHAR(30) NOT NULL,
//	`date` char(30) DEFAULT NOW(),
//	ban boolean default false,
//	mng boolean default false
//	);
//	CREATE TABLE `comment`(
//	id INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//	userid INT(5) NOT NULL,
//	articleid  INT(5) NOT NULL,
//	`date` CHAR(30) DEFAULT NOW(),
//	`body` TEXT,
//	commentreply int(5)
//	);
//	CREATE TABLE `like`(
//	articleid INT(5) NOT NULL,
//	userid INT(5) NOT NULL
//	);
//	
//	게시물 갯수 + 댓글 갯수 > 등급조정
}
