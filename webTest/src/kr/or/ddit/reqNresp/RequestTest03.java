package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest03.do")
public class RequestTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
//		String strYear = request.getParameter("year");
//		int year = Integer.parseInt(strYear);
		
		int year = Integer.parseInt(request.getParameter("year"));
		
		String result = null;
		
		// 1년 ==> 365.2422 일
		
		// 4의 배수 ==> 윤년 ==> 100의배수 평년 ==> 400의 배수 윤년
		if( (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ) {
			result = "윤년";
		}else {
			result = "평년";
		}
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>윤년, 평년 구하기</title></head>");
		out.println("<body>");
		
		out.println("<h3>입력한 " + year + "년은 " + result + " 입니다.</h3>");
		
		
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
