package kr.or.ddit.reqNresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forwardTest01.do")
public class ForwardTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
		- forward
			특정 서블릿이나 JSP에 대한 요청을 다른 서블릿이나 JSP로 넘겨준다.
			(이 때 HttpServletRequest객체와 HttpServletResponse객체를 공유한다.)
			웹브라우저의 URL주소는 처음 요청할 때의 값이 바뀌지 않는다.
			서버 내부에서만 접근이 가능하다.
			
	*/
		// 현재 문서에서 만든 데이터를 이동하는 문서로 전달하기
		// 이 때는 Request객체의 setAttribute()메서드를 이용하여 전달할 데이터를
		// 셋팅하여 보내고, 받는 쪽에서는 Request객체의 getAttribute()메서드를 이용해서
		// 읽어온다.
		
		// 보낼 때 형식) Request객체.setAttribute("key값", 데이터)
		//				==> 'key값'은 String, '데이터'는 자바의 모든 자료형
		
		// 받을 때 형식) Request객체.getAttribute("key값")
		
		request.setAttribute("tel", "010-1234-5678");
		
		
		// forward방식으로 이동하기
		//	==> Request객체의 getRequestDispatcher()메서드에 이동할 
		//		서블릿이나 JSP를 지정해 주는데 전체 URI경로 중에서
		//		Context Path 이후의 경로를 지정해 준다.
		//		예) 실제 경로가 '/webTest/forwardTest02.do'일 때 
		//			셋팅하는 경로 ==> '/forwardTest02.do'
		RequestDispatcher rd = request.getRequestDispatcher("/forwardTest02.do");
		rd.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
