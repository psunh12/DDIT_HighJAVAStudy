package kr.or.ddit.session.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() { }
	
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	

	@Override
	public MemberVO getLoginMember(MemberVO memVo) {
		MemberVO loginMemberVo = null;
		SqlSession session = MybatisUtil.getSqlSession();
		
		try {
			loginMemberVo = session.selectOne("member.getLoginMember", memVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return loginMemberVo;
	}
	
}
