package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.vo.MemberVO;


public class MemberMyBatisTest {
	Scanner scan = new Scanner(System.in);	
	static SqlSessionFactory sqlSessionFactory = null;
	
	public static void main(String[] args) {
		InputStream in = null;	// 스트림 객체 변수 선언
		
		try {
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		
		} catch (Exception e) {
			System.out.println("MyBatis 초기화 실패!!!");
			e.printStackTrace();
		}finally {
			if(in != null) try { in.close(); } catch (IOException e) { }
		}
		
		
		new MemberMyBatisTest().memberStart();
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
	
	private void updateMember2() {
	
	}

	private void displayMember()  {
	      System.out.println();
	      SqlSession session = null;
	      //전체 회원정보 가져오기
	      try {
	         session = sqlSessionFactory.openSession();
	      List<MemberVO> memList = session.selectList("member.getAllMember");
	      
	      System.out.println("-------------------------------------------------------");
	      System.out.println("ID\t비밀번호\t이름\t전화번호\t\t주소");
	      System.out.println("-------------------------------------------------------");
	      if(memList==null || memList.size()==0) {
	         System.out.println("\t 등록된 회원 정보가 하나도 없습니다...");
	      }else {
	         for(MemberVO memVo : memList) {
	            String memId = memVo.getMem_id();
	            String memPass = memVo.getMem_pass();
	            String memName = memVo.getMem_name();
	            String memTel = memVo.getMem_tel();
	            String memAddr = memVo.getMem_addr();
	            
	            System.out.println(memId + "\t" +memPass + "\t" + memName 
	                  + "\t" + memTel + "\t" + memAddr );
	         }
	      }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         session.close();
	      }

	}

	// 3. update
	private void updateMember() {
		
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요...");

			String memId;
			int count;
			do {
				System.out.println("수정할 회원ID 입력 >> ");
				memId = scan.next();

				count = session.selectOne("member.getMemberCount",memId);
				if(count==0) {
					System.out.println(memId + "는(은) 없는 회원ID 입니다...");
					System.out.println("수정 작업을 종료합니다...");
				}
			} while(count==0);

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

			// 입력한 자료를 VO객체에 저장한다.
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(memId);
			memVo.setMem_pass(newPass);
			memVo.setMem_name(newName);
			memVo.setMem_tel(newTel);
			memVo.setMem_addr(newAddr);

			int cnt = session.update("member.updateMember",memVo);

			if(cnt>0) {
				session.commit();
				System.out.println(memId + " 회원 정보 수정 성공!!!");
			}else {
				System.out.println(memId + " 회원 정보 수정 실패~~~");
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();

		}
	}

	// 2. delete
	private void deleteMember() {
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		
		System.out.print("삭제할 회원ID 입력 >> ");
		String memId = scan.next();
				
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();

			int cnt =session.delete("member.deleteMember",memId);
			
			if(cnt>0) {
				session.commit();
				System.out.println(memId + " 회원 정보 삭제 성공!!!");
			}else {
				System.out.println(memId + " 회원 정보 삭제 실패~~~");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}


	// 1. insert
	private void insertMember() {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();

		String memId = null; 	// 입력 받은 회원ID가 저장될 변수
		int count = 0;			// 아이디 유무 확인
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");
		do {
			System.out.print("회원ID 입력 >> ");
			memId = scan.next();
			
			count = session.selectOne("member.getMemberCount",memId);
			
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
		
		// 입력이 완료되면 입력한 데이터를 VO객체에 저장한다.
		MemberVO memVo =new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt =session.insert("member.insertMember", memVo);
		
		if(cnt>0) {
			session.commit();
			
			System.out.println(memId+" 회원 정보 추가 성공!!!");
		}else {
			System.out.println(memId+" 회원 정보 추가 실패 ~~~");
		}

	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		session.close();
	}
}

	// 메뉴를 출력하고 입력 받은 작업 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("  [[ 회 원 등 록   ]]");
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