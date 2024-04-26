package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	- Servlet의 동작 과정
	1. 사용자(클라이언트)가 URL을 클릭하면 HTTP요청을 Serlvlet Container로 전송한다. (요청작업)
	2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
		(로딩이 안된 경우에는 로딩을 한다. 처음 로딩시 init()메서드를 호출한다.)
		(서블릿 3.0이상에서는 애노테이션(@WebServlet)으로 설정한다.)
	3. 서블릿 컨테이너는 개별 요청을 처리할 쓰레드를 생성하여 해당 서블릿 객체의 service()메서드를
		호출한다.
		(이 때 HttpServletRequest객체와 HttServletResponse객체를 생성하여 파라미터로 넘겨준다.)
	4. service()메서드는 전송방식(GET, POST등)을 체크하여 그것에 맞는 메서드를 자동으로 호출한다.
		(doGet(), doPost(), doPut(), doDelete()등...)
	5. 요청 및 응답 처리가 모두 완료되면 HttpServletRequest객체와 HttServletResponse객체는
		자동으로 소멸된다.
	6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 호출된다.

*/

//@WebServlet(value="/servletTest03.do")  // value속성만 지정할 때는 value속성명을 생략할 수 있다.
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {

	@Override
	public void init() throws ServletException {
		
		System.out.println(this.getServletName() + "에서 init()메서드를 호출합니다...");
		
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("service()메서드 시작...");
		
		// Get방식과 Post방식에 맞는 메서드 호출하기
		
		// 방법1 ==> 부모 클래스인 HttpServlet의 service()메서드에게 위임하기
		//super.service(request, response);
		
		// 방법2 ==> 클라이언트의 전송방식을 직접 구분해서 호출하기
		String method = request.getMethod();  // 전송 방식을 구한다.
		if("GET".equals(method)) {
			doGet(request, response);
		}else if("POST".equals(method)) {
			doPost(request, response);
		}
		
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet()메서드 시작...");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body><h2 style='color:blue;'>doGet()메서드에서 처리한 내용입니다.</h2>");
		out.println("</body></html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()메서드 시작...");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body><h2 style='color:red;'>doPost()메서드에서 처리한 내용입니다.</h2>");
		out.println("</body></html>");
		
	}
	
	@Override
	public void destroy() {
		System.out.println(this.getServletName() + "에서 destroy()메서드가 호출되었습니다.");
	}
	
}












