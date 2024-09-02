<%@page import="kr.ac.kopo.util.AjaxResponse"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="kr.ac.kopo.account.dao.AccountDAO"%>
<%@page import="kr.ac.kopo.vo.MemberVO"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="kr.ac.kopo.stock.vo.StockVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.ac.kopo.util.UpdateStocksPrice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UpdateStocksPrice updateStocksPrice = new UpdateStocksPrice();
	
	updateStocksPrice.changeStockPriceRandom();
	Map<String, StockVO> stockMap = updateStocksPrice.getAllStockMap();
	
	boolean toggleSign = false;
	
	
	// 거래중인 자신의 주식과 가격이 같아지면 거래 진행
	MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	if (loginUser != null) {
		AccountDAO accountDAO = new AccountDAO();
		List<Map<String, Object>> tradingList = accountDAO.selectListTradingList(loginUser.getId());
		
		for (Map<String, Object> map : tradingList) {
			BigDecimal stockPrice = (BigDecimal)map.get("STOCK_PRICE");
			long stockP = stockPrice.longValue();
			if (stockMap.get(map.get("ITMS_NM")).getClpr() == stockP) {
				toggleSign = true;
				if (map.get("TRADING_TYPE").equals("B")){
					// 거래중인거 map의 TRADING_CD로 TRADING에서 삭제
					// TRADING_HOLDING 테이블에 삽입
					// TRADING_COMPLETE 테이블에 삽입
					accountDAO.tradingCompleteWork(map);
					System.out.println("구매완료");
				} else if (map.get("TRADING_TYPE").equals("S")){
					accountDAO.sellTradingCompleteWork(map);
					System.out.println("판매완료");
					stockMap.put("거래완료", new StockVO());
				}
			}
		}
	}
	AjaxResponse ajaxResponse = new AjaxResponse(stockMap, toggleSign);
	
	ObjectMapper mapper = new ObjectMapper();
	String jsonResponse = mapper.writeValueAsString(ajaxResponse);
	
	// Set response type and encoding
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    // Write JSON to response
    response.getWriter().write(jsonResponse);
%>