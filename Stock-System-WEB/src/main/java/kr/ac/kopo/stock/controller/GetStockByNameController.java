package kr.ac.kopo.stock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.account.vo.TradingVO;
import kr.ac.kopo.framework.Controller;
import kr.ac.kopo.stock.dao.StockDAO;
import kr.ac.kopo.stock.vo.StockVO;
import kr.ac.kopo.util.RedisUtil;
import kr.ac.kopo.vo.MemberVO;

public class GetStockByNameController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String itmsNm = request.getParameter("itmsNm");
		if (itmsNm == null) {
			itmsNm = "�Ｚ����";
		}
		System.out.println(itmsNm);
		StockDAO stockDAO = new StockDAO();
		System.out.println("getstock����");
		List<StockVO> stockList = stockDAO.selectListStockByName(itmsNm);
		
		StockVO lastDayStockVO = null;
		if (stockList != null) {
			lastDayStockVO = stockList.remove(0);
		}
		
		
		RedisUtil redisUtil = new RedisUtil("172.31.9.182", 6379);
		MemberVO redisMember = (MemberVO) redisUtil.getObject("loginUser");
		
		//���忡 �Ⱦ��� �ڵ�
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (redisMember != null) {
			AccountDAO accountDAO = new AccountDAO();
			List<AccountVO> accountList = accountDAO.selectListOwnAccount(redisMember.getId());
			request.setAttribute("accountList", accountList);
			//ȸ���� �������� ���¸���Ʈ���� �ŷ����� �ֽ� ��������
			//1. �������� �ֽ�
			Map<String, List<TradingVO>> buyStockMap = new HashMap<>();
			for (int i = 0; i < accountList.size(); i++) {
				buyStockMap.put(accountList.get(i).getAccountNum(), accountDAO.selectListTradingVOByAccountNum(accountList.get(i).getAccountNum()));
			}
			session.setAttribute("loginUser", redisMember);
			request.setAttribute("buyStockMap", buyStockMap);
		} else {
			session.invalidate();
		}
		// ��·� ���������� 15�� ��������
		List<StockVO> upList = stockDAO.selectAllRiseRateChart();
		request.setAttribute("upList", upList);
		// �϶��� ���������� 15�� ��������
		List<StockVO> downList = stockDAO.selectAllFallRateChart();
		request.setAttribute("downList", downList);
		
		request.setAttribute("stockList", stockList);
		request.setAttribute("lastDayStockVO", lastDayStockVO);
		request.setAttribute("last2DayClpr", stockList.get(0).getClpr());
		String formatted = String.format("%.2f", (double)((lastDayStockVO.getTrqu() - stockList.get(0).getTrqu())/stockList.get(0).getTrqu())*100);
		request.setAttribute("trquRate", formatted);
		return "/index.jsp";
	}
	
}
