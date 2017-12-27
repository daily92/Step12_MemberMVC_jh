package test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.member.dao.MemberDao;
import test.member.dto.MemberDto;

public class MemberInsertFormAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 회원 추가 폼만 출력해 주면 되기 때문에 수행할 business logic 은 없다.
			//그럼 바로 jsp 페이지로 넘어가면 되는 거 아닌가? 
			//아니야. view 페이지는 무조건 servlet 에서 forward 이동하는 애들 모아 놓은 거라 햇자나~~~
		
		// view 페이지의 정보를 담고 (forward 이동 경로)
		ActionForward af= new ActionForward("/views/member/insertform.jsp");
		// 리턴해 준다
		return af;
	}

}
