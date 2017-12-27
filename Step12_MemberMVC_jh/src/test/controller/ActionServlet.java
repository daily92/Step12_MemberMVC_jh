package test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//여러 request 을 이 ActionServlet 이 각각의 Action 에 분배해주는 역할
@WebServlet("*.do")
public class ActionServlet extends HttpServlet{	
	// . 을 포함한 요청 확장자의 길이 (.do 니까 3 글자/이 숫자가 프로젝트 상황에 따라 달라짐. 이 페이지에서 바꾸는 것 이것뿐!!)
	public static final int INCLUDE_EXTENSION_LENGTH= 3;
	
	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 요청 URI 읽어오기
		String uri= request.getRequestURI();
		// context name 읽어오기
		String contextName= request.getContextPath();
		// 순수 요청 경로(command) 를 추출 한다.
		// command => /fortune or /showtime
			/* 
			 * substring ex. "hamburger".substring(4, 8) returns "urge"
			 * 즉, uri.substring(contextName.length(), uri.length()-INCLUDE_EXTENSION_LENGTH); 는
			 * uri 에서 특정 string을 추출subtract 하는데, 
			 * contextName.length() 이후부터 uri.length()-INCLUDE_EXTENTSION_LENGTH (.do 세 글자) 까지 추출
			 * 즉 .do 가 붙은 부분의 .do 를 제외한 이름만 남음!
			 */
		String command= uri.substring(contextName.length(), uri.length()-INCLUDE_EXTENSION_LENGTH);
		
		// 해당 command 를 수행(처리)할 Action 객체를 팩토리로부터 얻어온다. 
			//UserActionFactory 를 참조해서 action 메소드 호출해서 command 전달해서
			//추상클래스 Action 에 action 으로 저장
			//command 자리에 /fortune 들어감
		Action action= UserActionFactory.getInstance().action(command);
		
		//Redirect("나한테 이런 경로로 새로운 요청을 다시 해!" 라는 응답) | Forward ("응답을 네가 대신 좀 해" 응답을 위임하는 거)
		//redirect, forward 둘 다 이동할(요청) 경로가 필요해 => 요청경로는 af 변수에 담겨져 (af.getPath() 로 얻어냄)
		if(action!= null){// 처리 가능한 command 라면 
			ActionForward af= null;
			//ActionForward 에는 두 가지 들어 있어 1)요청 처리 2)어디로 이동 할 것인지(요청 경로/redirect 인지 forward 인지)
			try{
				//af 객체는 action.execute() 로 리턴해줌
				af= action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			//요청 경로가 redirect 인지 forward 인지 여기서 알아봄
			if(af.isRedirect()){ // redirect 이동해야 한다면 => .sendRedirect()
				// redirect 이동 시킨다.
				response.sendRedirect(contextName+af.getPath());
			}else{// forward 이동해야 한다면 => request.getRequestDispatcher()
				RequestDispatcher rd= request.getRequestDispatcher(af.getPath());
				rd.forward(request, response);
			}
		}	
	}
}	
