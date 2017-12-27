package test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.member.dao.MemberDao;
import test.member.dto.MemberDto;

public class MemberUpdateAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num= Integer.parseInt(request.getParameter("num"));
		String name= request.getParameter("name");
		String addr= request.getParameter("addr");
		
		MemberDto dto= new MemberDto(num,name,addr);
		MemberDao.getInstance().update(dto);
		
		// 3. 응답 ? (오 새로운 방법이다!!!!)
		// 응답 메세지를 request 에 담기
		request.setAttribute("msg", "회원 정보를 수정 했습니다");
		// 확인을 눌렀을 때 이동할 url 을 request 에 담기
		request.setAttribute("url", "/member/list.do");
		// 알림 페이지 view 로 forward 이동할 수 있도록		
		ActionForward af= new ActionForward("/views/member/alert.jsp");
		return af;

	}

}
