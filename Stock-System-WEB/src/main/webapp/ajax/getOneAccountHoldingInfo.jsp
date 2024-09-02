<%@page import="kr.ac.kopo.util.HoldingAccountResponse"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="kr.ac.kopo.account.vo.StockHoldingVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.ac.kopo.account.dao.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	request.setCharacterEncoding("utf-8");
	
	String accountNum = request.getParameter("accountNum");
	AccountDAO accountDAO = new AccountDAO();
	List<StockHoldingVO> stockHoldingVO = accountDAO.selectListHoldingStockByAccountNum(accountNum);
	long balance = accountDAO.selectOneAccountInfo(accountNum).getBalance();
	
	HoldingAccountResponse holdingAccountResponse = new HoldingAccountResponse(stockHoldingVO, balance);
	ObjectMapper mapper = new ObjectMapper();
    String jsonResponse = mapper.writeValueAsString(holdingAccountResponse);
    
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(jsonResponse);
%>