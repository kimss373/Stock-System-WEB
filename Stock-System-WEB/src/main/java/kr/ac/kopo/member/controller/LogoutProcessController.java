package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.framework.Controller;
import kr.ac.kopo.util.RedisUtil;

public class LogoutProcessController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		RedisUtil redisUtil = new RedisUtil("172.31.9.182", 6379);
		redisUtil.deleteKey("loginUser");
		return "/getOneStock.do?itmsNm=»ï¼ºÀüÀÚ";
	}

	
}
