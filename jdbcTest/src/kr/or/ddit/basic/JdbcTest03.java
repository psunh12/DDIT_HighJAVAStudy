package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) Lprod_ID값을 2개 입력 받아 두 값 중 작은 값부터
//		 큰 값 사이의 자료들을 출력하시오.

public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 Lprod_id값 입력 >> ");
		int num1 = scan.nextInt();
		
		System.out.print("두번째 Lprod_id값 입력 >> ");
		int num2 = scan.nextInt();
		
		int small, big;
//		if(num1>num2) {
//			small = num1;
//			big = num2;
//		}else {
//			small = num2;
//			big = num1;
//		}
		
		big = Math.max(num1, num2);
		small = Math.min(num1, num2);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "sem", "java");
			
//			String sql = "select * from lprod where lprod_id >= " + small 
//							+ " and lprod_id <= " + big;
			String sql = "select * from lprod where lprod_id between " + small 
					+ " and " + big;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("  == 검색 결과 ==");
			while(rs.next()) {
				System.out.println("ID : " + rs.getInt("Lprod_Id"));
				System.out.println("GU : " + rs.getString("Lprod_gu"));
				System.out.println("NM : " + rs.getString("Lprod_nm"));
				System.out.println("------------------------------------");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); }catch(SQLException e ) {}
			if(stmt!=null) try { stmt.close(); }catch(SQLException e ) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e ) {}
		}
		
		
		
		
		
		

	}

}
