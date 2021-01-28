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
//	USE Board;
//	CREATE TABLE article(
//	aid INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//	title CHAR(30) NOT NULL,
//	`body` TEXT  NOT NULL,
//	uid INT(5) UNSIGNED NOT NULL,
//	regDate DATE DEFAULT NOW(),
//	`like` INT(5) DEFAULT 0,
//	hit INT(5) DEFAULT 0,
//	FOREIGN KEY (uid) REFERENCES `member`(uid)
//	);
//	CREATE TABLE `member`(
//	uid INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//	ID CHAR(10) NOT NULL,
//	PW CHAR(15) NOT NULL,
//	nickname CHAR(15) NOT NULL,
//	email CHAR(25) NOT NULL,
//	regDate DATE DEFAULT NOW(),
//	ban BOOLEAN DEFAULT FALSE,
//	mng BOOLEAN DEFAULT FALSE
//	);
//	CREATE TABLE `comment`(
//	cid INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
//	uid INT(5) UNSIGNED NOT NULL,
//	aid INT(5) UNSIGNED NOT NULL,
//	regDate DATE DEFAULT NOW(),
//	`body` TEXT,
//	commentreply INT(5),
//	FOREIGN KEY (aid) REFERENCES article(aid),
//	FOREIGN KEY (uid) REFERENCES MEMBER(uid)
//	);
//	CREATE TABLE `like`(
//	aid INT(5) UNSIGNED NOT NULL,
//	uid INT(5) UNSIGNED NOT NULL,
//	FOREIGN KEY (aid) REFERENCES article(aid),
//	FOREIGN KEY (uid) REFERENCES MEMBER(uid)
//	);
}
