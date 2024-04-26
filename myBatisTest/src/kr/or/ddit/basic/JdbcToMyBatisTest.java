package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

public class JdbcToMyBatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
//		
//		InputStream in = null;
//		SqlSessionFactory sqlSessionFactory = null;
//		try {
//			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(in!=null) try { in.close(); }catch(IOException e) {}
//		}
		//---------------------------------------------------------------
		SqlSession session = null;
		try {
//			session = sqlSessionFactory.openSession();
			session = MybatisUtil.getSqlSession();
			
			int maxId = session.selectOne("jdbc.getMaxid"); 
			maxId++;
			
			//---------------------------------------
			String gu;
			int count = 0;
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.next();
				
				count = session.selectOne("jdbc.getLprodCount", gu);
				
				if(count>0) {
					System.out.println("입력한 상품 분류 코드 " + gu 
													+ "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요...");
					System.out.println();
				}
				
			}while(count>0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();
			
			// 입력하거나 구해진 데이터를 VO에 저장
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(maxId);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			int cnt = session.insert("jdbc.insertLprod", lvo);
			
			if(cnt>0) {
				session.commit();
				
				System.out.println("등록 성공!!!");
			}else {
				System.out.println("등록 실패~~~");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

}
