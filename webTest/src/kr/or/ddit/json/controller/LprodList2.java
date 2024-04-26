package kr.or.ddit.json.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.json.service.ILprodService;
import kr.or.ddit.json.service.LprodServiceImpl;
import kr.or.ddit.vo.LprodVO;


@WebServlet("/lprod/lprodList2.do")
public class LprodList2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ILprodService service = LprodServiceImpl.getInstance();
		
		// DB에서 데이터 가져오기
		List<LprodVO> lprodList = service.getAllLprod();
		
		// View로 전달할 데이터 셋팅하기
		request.setAttribute("lprodList", lprodList);
		
		// View로 forwarding한다.
		request.getRequestDispatcher("/basic/json/lprodList.jsp")
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
