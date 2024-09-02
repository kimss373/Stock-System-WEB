<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.ac.kopo.account.vo.StockHoldingVO"%>
<%@page import="kr.ac.kopo.util.AccountResponse"%>
<%@page import="kr.ac.kopo.account.vo.TradingVO"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="kr.ac.kopo.account.vo.AccountVO"%>
<%@page import="kr.ac.kopo.account.dao.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String accountNum = request.getParameter("accountNum");
	
	AccountDAO accountDAO = new AccountDAO();
	AccountVO accountResult = accountDAO.selectOneAccountInfo(accountNum);
	List<TradingVO> tradingResult = accountDAO.selectListTradingVOByAccountNum(accountNum);
	List<StockHoldingVO> stockHoldingResult = accountDAO.selectListHoldingStockByAccountNum(accountNum);
	Map<String, StockHoldingVO> holdingMapResult = new HashMap<>();
	for (int i = 0; i < stockHoldingResult.size(); i++) {
		holdingMapResult.put(stockHoldingResult.get(i).getItmsNm(), stockHoldingResult.get(i));
	}
	
	AccountResponse accountResponse = new AccountResponse(accountResult, tradingResult, holdingMapResult);
	
	// Convert AccountVO to JSON
    ObjectMapper mapper = new ObjectMapper();
    String jsonResponse = mapper.writeValueAsString(accountResponse);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(jsonResponse);
	
%>