<%@page import="kr.ac.kopo.account.vo.AccountVO"%>
<%@page import="kr.ac.kopo.account.dao.AccountDAO"%>
<%@page import="kr.ac.kopo.account.vo.TradingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String buyOrSell = request.getParameter("buyOrSell");
	String accountNum = request.getParameter("accountNum");
	String srtnCd = request.getParameter("srtnCd");
	String itmsNm = request.getParameter("itmsNm");
	long stockPrice = Long.parseLong(request.getParameter("stockPrice"));
	long stockCount = Long.parseLong(request.getParameter("stockCount"));
	TradingVO tradingVO = new TradingVO(accountNum, srtnCd, stockPrice, stockCount);
	
	AccountDAO accountDAO = new AccountDAO();
	// 매수
	String msg = "등록성공";
	if (stockCount != 0) {
		if (buyOrSell.equals("buy")) {
			// 가격 * 구매수량 < 계좌잔액인지 확인
			long totalPrice = stockPrice * stockCount;
			
			// tradingVO에 거래 종류 설정
			tradingVO.setTradingType("B");
			
			AccountVO accountVO = accountDAO.selectOneAccountInfo(accountNum); 
			if (accountVO.getBalance() < totalPrice) {
				msg = "등록실패(잔액부족)";
			} else {
				// trading 테이블에 삽입
				accountDAO.insertNewTradingTransaction(tradingVO);
			}
		} else if (buyOrSell.equals("sell")) {
			// tradingVO에 거래 종류 설정
			tradingVO.setTradingType("S");
			
			accountDAO.insertNewTradingTransaction(tradingVO);
			
		}
	} else {
		msg = "수량을 선택하세요";
	}


%>
{
	"msg" : "<%=msg %>"
}