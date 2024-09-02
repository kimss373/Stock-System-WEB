<%@page import="java.util.List"%>
<%@page import="kr.ac.kopo.stock.dao.StockDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.google.gson.Gson" %>
<%
	request.setCharacterEncoding("utf-8");

	String query = request.getParameter("query");
	
	StockDAO stockDAO = new StockDAO();
	List<String> list = stockDAO.selectALlStockName(query);
	
	Gson gson = new Gson();
	String jsonResponse = gson.toJson(list);
	
	response.setContentType("application/json"); // Content-Type 설정
    response.setCharacterEncoding("UTF-8"); // 문자 인코딩 설정
	
	out.print(jsonResponse);
	out.flush();
	
%>



