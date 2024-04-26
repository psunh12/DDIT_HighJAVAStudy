package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.service.JdbcBoardServiceImpl;
import kr.or.ddit.vo.BoardVO;

public class JdbcBoardController {
	private IJdbcBoardService service;
	private Scanner scan;
	
	public JdbcBoardController() {
		service = JdbcBoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().startBoard();
	}
	
	// 시작 메서드
	public void startBoard() {
		String title = null;
		
		
		while(true) {
			int choice = displayMenu(title);
			title = null;
			
			switch(choice) {
				case 1 :		// 새글 작성 
					insertBoard(); break;
				case 2 : 		// 게시글 보기
					viewBoard(); break;
				case 3 : 		// 검색
					title = searchBoard(); break;
				case 0 : 		// 작업끝..
					System.out.println("게시판 프로그램 종료~~~");
					 return;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요...");
			}
		}
	}
	
	// 게시글의 검색 단어를 입력 받아 반환하는 메서드
	private String searchBoard() {
		System.out.println();
		scan.nextLine(); // 버퍼 비우기
		
		System.out.println(" 검색 작업");
		System.out.println("------------------------------------------");
		System.out.print("검색할 제목 입력 >> ");
		return scan.nextLine();
	}
	
	
	// 게시글 내용을 보여주는 메서드
	private void viewBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int num = scan.nextInt();
		
		// 입력한 게시글 번호에 맞는 게시글 정보 가져오기
		BoardVO boardVo = service.getBoard(num);
		
		if(boardVo==null) {
			System.out.println(num + "번의 게시글이 존재하지 않습니다...");
			return;
		}
		
		System.out.println();
		System.out.println( boardVo.getBoard_no() + "번 글 내용");
		System.out.println("-------------------------------------------------");
		System.out.println(" - 제  목 : " + boardVo.getBoard_title());
		System.out.println(" - 작성자 : " + boardVo.getBoard_writer());
		System.out.println(" - 내  용 : " + boardVo.getBoard_content());
		System.out.println(" - 작성일 : " + boardVo.getBoard_date());
		System.out.println(" - 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("-------------------------------------------------");
		System.out.println("메뉴 : 1.수정    2.삭제    3.리스트로 가기");
		System.out.print("작업 선택 >> ");
		
		int choice = scan.nextInt();
		
		switch(choice) {
			case 1 :		// 수정 
				updateBoard(num); break;
			case 2 : 		// 삭제
				deleteBoard(num); break;
			case 3 : 		// 리스트로 가기
				return;
		}
		
	}
	
	// 게시글을 삭제하는 메서드
	private void deleteBoard(int num) {
		int cnt = service.deleteBoard(num);
		
		if(cnt>0) {
			System.out.println(num + "번 글이 삭제 되었습니다...");
		}else {
			System.out.println(num + "번 글 삭제 실패~~~");
		}
	}
	
	
	// 게시글을 수정하는 메서드
	private void updateBoard(int num) {
		System.out.println();
		scan.nextLine();   // 버퍼 비우기
		
		System.out.println("  수정 작업하기");
		System.out.println("------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		
		// 입력한 데이터 VO에 저장하기
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_no(num);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt>0) {
			System.out.println(num + "번 글이 수정 되었습니다...");
		}else {
			System.out.println(num + "번 글 수정 작업 실패~~~");
		}
	}
	
	
	// 새글을 작성하는 메서드
	private void insertBoard() {
		System.out.println();
		scan.nextLine(); // 입력 버퍼 비우기
		
		System.out.println("   새 글 작성하기");
		System.out.println("------------------------------");
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();
		
		// 입력 받은 정보를 VO객체에 저장하기
		BoardVO boardVo = new BoardVO();
		
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0) {
			System.out.println("새 글이 추가 되었습니다...");
		}else {
			System.out.println("새 글 추가 실패~~~");
		}
		
	}
	
	
	// 게시판 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 작업 번호를 반환하는 메서드
	private int displayMenu(String title) {
		
		List<BoardVO> boardList; 
		
		if(title==null) {
			// 전체 데이터 가져오기
			boardList = service.getAllBoard();
		}else {
			// 검색한 데이터 가져오기
			boardList = service.getSearchBoard(title);
		}
		
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println(" No	        제 목            작성자 	  조회수 ");
		System.out.println("-----------------------------------------------");
		
		if(boardList==null || boardList.size()==0) {
			System.out.println("\t출력할 게시글이 하나도 없습니다...");
		}else {
			for(BoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t" + 
						boardVo.getBoard_title() + "\t" +
						boardVo.getBoard_writer() + "\t" +
						boardVo.getBoard_cnt());
			}
		}
		System.out.println("-----------------------------------------------");
		System.out.println("메뉴 : 1.새글작성  2.게시글보기  3.검색  0.작업끝");
		System.out.print("작업선택 >> ");
		
		return scan.nextInt();
	}
	
	

}












