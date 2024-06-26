package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

// JDBC드라이버를 로딩하고
// Connection객체를 생성하여 반환하는 메서드로 구성된 Class 작성하기
// (dbinfo.properties의 내용으로 설정하기)

// ResourceBundle객체 이용하기
public class DBUtil3 {
	private static Logger logger = Logger.getLogger(DBUtil3.class);
	
	private static ResourceBundle bundle; // ResourceBundle객체변수 선언
	// 초기화 블럭
	static {	// static 생략 가능
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		logger.debug("ResourceBundel객체 >> "+bundle);
		logger.info("ResourceBundle객체 생성완료... --> dbinfo.propertis파일 읽기");
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			logger.info("DB드라이버 로딩 성공~~~");
			
		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패~~~");
			logger.error("DB드라이버 로딩 실패!!!", e);
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn =null;
		try {
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe","pc01","java");
			conn = DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			logger.debug("Connection객체 생성 >> "+conn);
			logger.info("DB연결 성공!!!");
		
		} catch (SQLException e) {
//			System.out.println("DB연결 실패~~~");
			logger.error("DB연결 실패~~~",e);
			e.printStackTrace();
		}
		
		return conn;
	}
}
