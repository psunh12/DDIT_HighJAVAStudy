package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.BoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {

	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() { }
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImpl();
		
		return dao;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;	// 반환값이 저장될 변수 선언
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("board.insertBoard", boardVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("board.updateBoard", boardVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.delete("board.deleteBoard", boardNo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		SqlSession session = null;
		List<BoardVO> boardList = null;  // 반환값이 저장될 변수 선언
		try {
			session = MybatisUtil.getSqlSession();
			
			boardList = session.selectList("board.getAllBoard");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> getSearchBoard(String title) {
		SqlSession session = null;
		List<BoardVO> boardList = null;  // 반환값이 저장될 변수 선언
		try {
			session = MybatisUtil.getSqlSession();
			
			boardList = session.selectList("board.getSearchBoard", title);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return boardList;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO boardVo = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			boardVo = session.selectOne("board.getBoard", boardNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return boardVo;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("board.setCountIncrement", boardNo); 
			
			if(cnt>0) session.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

}











