package test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클래스 생성할 때 abstract 박스 체크!
public abstract class Action {
	// 추상 메소드 (Action 에 mouseone, abstract 에 mouseon (add))
	public abstract ActionForward execute(HttpServletRequest request, HttpServletResponse response);
	
}
