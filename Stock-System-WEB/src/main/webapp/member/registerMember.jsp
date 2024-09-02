<%@page import="kr.ac.kopo.member.dao.MemberDAO"%>
<%@page import="kr.ac.kopo.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("memberId");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String personalNum = request.getParameter("personalNum1") + request.getParameter("personalNum2");
	
	MemberVO memberVO = new MemberVO(id, pwd, name, hp, personalNum);
	System.out.println(memberVO);
	MemberDAO memberDAO = new MemberDAO();
	memberDAO.insertRegisterMember(memberVO);

	String getId = "yes";
	
%>
{
	"getId" : "<%=getId %>"
}