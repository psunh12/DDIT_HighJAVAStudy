package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.vo.BoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
	private IJdbcBoardDao dao;
	
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) service = new JdbcBoardServiceImpl();
		
		return service;
	}
	

	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public List<BoardVO> getSearchBoard(String title) {
		return dao.getSearchBoard(title);
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		// 게시글 내용을 가져올 때 조회수를 증가하는 작업이 선행되어야 하기 때문에
		// 이 작업을 service의 메서드에서 함께 처리한다.
		int cnt = dao.setCountIncrement(boardNo);
		
		if(cnt==0) return null;
		
		return dao.getBoard(boardNo);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
