package test.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.member.dao.MemberDao;
import test.member.dto.MemberDto;
/*
 *  회원 목록을 DB 에서 불러 오는 business logic 을 처리하는 Action
 *  servlet 이 jsp 페이지로 이동할 때, 전달할 data 를 request 객체에 담아서!!
 */
public class MemberListAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 1. 회원 목록 얻어오기
		List<MemberDto> list= MemberDao.getInstance().getList();
		
		// 2. request 에 담기  => request.setAttribute("key", value);
		/*
		 * ("list" 라는 key 값으로 List<MemberDto> type 의 list 을 담았다! 
		 * 어떤 key 값으로 어떤 type 을 담았는 지 !이걸 기억해라 => jsp 페이지에서 불러올 때 사용)
		 * jsp 페이지에서 .getAttribute("key") 이용해서 불러옴
		 * => List<MemberDto> list=(List<MemberDto>)request.getAttribute("list");
		 */
		request.setAttribute("list", list);
		
		// 3. view 페이지로 forward 이동할 수 있도록 ActionForward 객체를 생성해서 (주소는 / (WebContent)부터 시작)
		ActionForward af= new ActionForward("/views/member/list.jsp");
		
		// 리턴해준다
		return af;
	}

}
