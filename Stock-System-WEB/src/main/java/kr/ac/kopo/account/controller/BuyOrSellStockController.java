package kr.ac.kopo.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.account.vo.TradingVO;
import kr.ac.kopo.framework.Controller;
import kr.ac.kopo.stock.dao.StockDAO;

public class BuyOrSellStockController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
		if (buyOrSell.equals("buy")) {
			// 가격 * 구매수량 < 계좌잔액인지 확인
			long totalPrice = stockPrice * stockCount;
			
			// tradingVO에 거래 종류 설정
			tradingVO.setTradingType("B");
			
			AccountVO accountVO = accountDAO.selectOneAccountInfo(accountNum); 
			if (accountVO.getBalance() < totalPrice) {
				request.setAttribute("msg", "거래실패(잔액부족)");
				return "/jobsFail.jsp";
			}
			// trading 테이블에 삽입
			accountDAO.insertNewTradingTransaction(tradingVO);
			
		} else if (buyOrSell.equals("sell")) {
			// tradingVO에 거래 종류 설정
			tradingVO.setTradingType("S");
			
			accountDAO.insertNewTradingTransaction(tradingVO);
			
		}
		
		return "/toHome.jsp?itmsNm=" + itmsNm;
	}

}
