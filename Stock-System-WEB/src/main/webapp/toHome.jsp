<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String itmsNm = "삼성전자";
	String requestItmsNm = request.getParameter("itmsNm");
	if (requestItmsNm != null) {
		itmsNm = requestItmsNm;
	}
%>
<script>
	location.href="/Stock-System-WEB/getOneStock.do?itmsNm=" + "<%=itmsNm %>"
			
</script>