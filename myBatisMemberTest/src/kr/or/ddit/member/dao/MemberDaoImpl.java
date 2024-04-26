package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() { }
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	

	@Override
	public int insertMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;		// 반환값이 저장될 변수
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("member.insertMember", memVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;		// 반환값이 저장될 변수
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.delete("member.deleteMember", memId);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}
	
	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;		// 반환값이 저장될 변수
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("member.updateMember", memVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> memList = null;  // 반환값이 저장될 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			memList = session.selectList("member.getAllMember");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlSession session = null;
		int count = 0;   // 반환값이 저장될 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			count = session.selectOne("member.getMemberCount", memId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlSession session = null;
		int cnt = 0;		// 반환값이 저장될 변수
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("member.updateMember2", paramMap);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

}







