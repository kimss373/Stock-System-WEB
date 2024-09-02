package kr.ac.kopo.member.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.framework.Controller;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.util.RedisSessionUtil;
import kr.ac.kopo.util.RedisUtil;
import kr.ac.kopo.vo.MemberVO;

public class LoginProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RedisUtil redisUtil = new RedisUtil("172.31.9.182", 6379);
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		
		MemberDAO memberDAO = new MemberDAO();
		MemberVO loginUser = null;
		loginUser = memberDAO.selectOneByIdPwd(memberVO);
		
		if (loginUser == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('아이디 비밀번호를 확인하세요'); location.href='/Stock-System-WEB/getOneStock.do?itmsNm=삼성전자';</script>"); 
			writer.close();
			return null;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			redisUtil.saveObject("loginUser", loginUser);
		}
		
		return "/toHome.jsp";
	}

	
	
}
