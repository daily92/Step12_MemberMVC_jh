package test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.member.dao.MemberDao;

public class MemberDeleteAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 1. 삭제할 회원의 번호를 읽어와서
		int num= Integer.parseInt(request.getParameter("num"));
		
		// 2. DB 에서 삭제하고
		MemberDao.getInstance().delete(num);
		
		// 3. 응답 ? (Redirect)
		
		// 알림 페이지 view 로 forward 이동할 수 있도록		
		ActionForward af= new ActionForward("/member/list.do");
		af.setRedirect(true);	// Redirect 응답하도록 
		
		return af;
	}

}
