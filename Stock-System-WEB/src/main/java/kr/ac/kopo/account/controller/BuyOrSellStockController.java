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
		// �ż�
		if (buyOrSell.equals("buy")) {
			// ���� * ���ż��� < �����ܾ����� Ȯ��
			long totalPrice = stockPrice * stockCount;
			
			// tradingVO�� �ŷ� ���� ����
			tradingVO.setTradingType("B");
			
			AccountVO accountVO = accountDAO.selectOneAccountInfo(accountNum); 
			if (accountVO.getBalance() < totalPrice) {
				request.setAttribute("msg", "�ŷ�����(�ܾ׺���)");
				return "/jobsFail.jsp";
			}
			// trading ���̺� ����
			accountDAO.insertNewTradingTransaction(tradingVO);
			
		} else if (buyOrSell.equals("sell")) {
			// tradingVO�� �ŷ� ���� ����
			tradingVO.setTradingType("S");
			
			accountDAO.insertNewTradingTransaction(tradingVO);
			
		}
		
		return "/toHome.jsp?itmsNm=" + itmsNm;
	}

}
