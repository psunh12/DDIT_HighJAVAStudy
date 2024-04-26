package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

/*
	회원 관리를 하는 프로그램을 작성하시오.( MYMEMBER 테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오.
	메뉴예시)
	------------------
	1. 자료 추가				--> insert ( C )
	2. 자료 삭제				--> delete ( D )
	3. 자료 수정				--> update ( U )
	4. 전체 자료 출력		--> select ( R )
	0. 작업 끝...
	------------------
	
	조건)
	1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받는다.)
	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	3) 자료 수정에서 '회원ID'는 변경되지 않는다.
	
----------------------------------------------------------------------
	MVC패턴, Singleton패턴 공부해 오기... 
	
*/
public class JdbcTest06 {
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new JdbcTest06().memberStart();
	}
	
	// 시작 메서드
	public void memberStart() {
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
				case 1 : 	// 추가
					insertMember(); break;
				case 2 :	// 삭제
					deleteMember(); break;
				case 3 :	// 수정
					updateMember(); break;
				case 4 :	// 전체 출력
					displayMember(); break;
				case 5 :	// 수정2
					updateMember2(); break;
				case 0 :	// 종료
					System.out.println("작업을 마칩니다...");
					return;
				default : 
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요...");
			}
			
		}
		
	} // 시작 메서드 끝...
	
	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 수정
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("수정할 회원ID 입력 >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0) {
			System.out.println(memId + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 종료합니다...");
			return;
		}	
		
		int num ;  // 수정할 항목 중 선택한 항목의 번호가 저장될 변수
		String updateField = null;		// 수정할 컬럼명이 저장될 변수
		String updateTitle = null;		// 
		
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요...");
			System.out.println("1.비밀번호   2.회원이름   3.전화번호   4.회원주소");
			System.out.println("------------------------------------------------");
			System.out.print("수정할 항목 선택 >> ");
			num = scan.nextInt();
			if(num<1 || num>4) {
				System.out.println("수정할 항목을 잘못 선택했습니다. 다시 선택하세요...");
			}
		}while(num<1 || num>4);
		
		switch(num) {
			case 1 : updateField = "mem_pass"; updateTitle = "비밀번호"; break;
			case 2 : updateField = "mem_name"; updateTitle = "회원이름"; break;
			case 3 : updateField = "mem_tel"; updateTitle = "전화번호"; break;
			case 4 : updateField = "mem_addr"; updateTitle = "회원주소"; break;
		}
		
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateTitle + " 입력 >> ");
		String updateData = scan.nextLine();
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set " + updateField + " = ? "
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "회원 정보 수정 작업 완료!!!");
			}else {
				System.out.println(memId + "회원 정보 수정 작업 실패~~~");
			}		 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
		
		
		
	}
	
	
	
	// 회원 정보를 수정하는 메서드 ==> 전체 항목 수정
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("수정할 회원ID 입력 >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0) {
			System.out.println(memId + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 종료합니다...");
			return;
		}
		
		System.out.println();
		System.out.println("수정할 내용을 입력하세요...");
		System.out.print("새로운 비밀번호 입력 >> ");
		String newPass = scan.next();
		
		System.out.print("새로운 회원이름 입력 >> ");
		String newName = scan.next();
		
		System.out.print("새로운 전화번호 입력 >> ");
		String newTel = scan.next();
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.print("새로운 회원주소 입력 >> ");
		String newAddr = scan.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_pass = ?, mem_name = ?,  "
					+ "	mem_tel = ? , mem_addr = ? "
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPass);
			pstmt.setString(2, newName);
			pstmt.setString(3, newTel);
			pstmt.setString(4, newAddr);
			pstmt.setString(5, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "회원 정보 수정 작업 완료!!!");
			}else {
				System.out.println(memId + "회원 정보 수정 작업 실패~~~");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
	}
	
	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		
		System.out.print("삭제할 회원ID 입력 >> ");
		String memId = scan.next();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + " 회원 정보 삭제 성공!!!");
			}else {
				System.out.println(memId + " 회원 정보 삭제 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
		
		
	}
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println();
			System.out.println("-------------------------------------------------------------");
			System.out.println(" ID   비밀번호    이름      전화번호          주소");
			System.out.println("-------------------------------------------------------------");
			
//			conn = DBUtil.getConnection();
//			conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				count++;
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				System.out.println(memId + "\t" + memPass + "\t" + memName
							+ "\t" + memTel + "\t" + memAddr);
			}
			
			if(count==0) {
				System.out.println("\t등록된 회원 정보가 하나도 없습니다...");
			}
			
			System.out.println("-------------------------------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); }catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
		
	}
	
	
	// 회원 정보를 추가(insert)하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");
		
		int count = 0;
		String memId = null; 	// 입력 받은 회원ID가 저장될 변수
		do {
			System.out.print("회원ID 입력 >> ");
			memId = scan.next();
			
			count = getMemberCount(memId);
			
			if(count>0) {
				System.out.println(memId + "은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력 하세요...");
			}
			
		}while(count>0);
		
		System.out.print("비밀번호 입력 >> ");
		String memPass = scan.next();
		
		System.out.print("회원이름 입력 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 입력 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("회원주소 입력 >> ");
		String memAddr = scan.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ " values( ?, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + " 회원 정보 추가 완료!!!");
			}else {
				System.out.println(memId + " 회원 정보 추가 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
		
		
	}
	
	// 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); }catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
		return count;
	}
	
	// 메뉴를 출력하고 입력 받은 작업 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("------------------");
		System.out.println("1. 자료 추가	");
		System.out.println("2. 자료 삭제	");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 자료 수정2");		// 원하는 항목 1개만 수정하기
		System.out.println("0. 작업 끝...");
		System.out.println("------------------");
		System.out.print("원하는 작업 선택 >> ");
		return scan.nextInt();
	}
	

}



















