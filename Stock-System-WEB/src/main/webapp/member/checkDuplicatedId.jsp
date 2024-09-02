<%@page import="kr.ac.kopo.member.dao.MemberDAO"%>
<%@page import="kr.ac.kopo.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("memberId");
	MemberDAO memberDAO = new MemberDAO();
	
	MemberVO result = memberDAO.selectOneMemberById(id);
	String getId = "no";
	if (result == null) {
		getId = "yes";
	}
	
%>
{
	"getId" : "<%=getId %>"
}