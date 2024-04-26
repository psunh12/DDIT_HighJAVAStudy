package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/redirectTest01.do")
public class RedirectTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	/*	
		- redirect
			다른 페이지로 넘어가도록 한다.
			응답시 브라우저에게 '이동할 URL'을 전송하여 브라우저가 해당 URL로 이동하는 방식이다.
			이동할 때는 'GET'방식으로 요청하기 때문에 URL의 길이에 제한이 있다.
			
			현재 문서와 이동할 문서 사이에 HttpServletRequest객체와 HttpServeltResponse객체는
			서로 공유하지 못한다. (이유: 브라우저에서 새롭게 요청하기 때문에)
	*/
//		request.setAttribute("tel", "010-1111-1111");
//		response.sendRedirect(request.getContextPath() + "/redirectTest02.do");
		
		// 클라이언트가 보내온 파라미터 데이터를 Redirect문서에 보내려면
		// 현재 문서에 파라미터 데이터를 받아서 Redirect문서의 URL주소에 
		// GET방식으로 붙여서 전송해야 한다.
		String userName = request.getParameter("username");  // 파라미터 데이터
		
		String tel = "010-1111-1111";  // 보낼 데이터
		
		// redirect방식으로 이동하기
		// 		==> Response객체의 sendRedirect()메서드를 이용한다.
		//		==> sendRedirect()메서드에는 이동할 서블릿이나 JSP문서의 전체 URL경로를 
		//			지정해 주어야 한다.
		//		==> 만약 이동할 URL경로에 한글이 포함될 경우에는 URLEncoder객체의
		//			encode()메서드로 인코딩해서 지정해 주어야 한다.
		
		response.sendRedirect(request.getContextPath() + 
			"/redirectTest02.do?name=" + URLEncoder.encode(userName, "utf-8") + "&tel=" + tel);

//		// 다른 서버로 이동하기
//		response.sendRedirect("https://www.naver.com");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
