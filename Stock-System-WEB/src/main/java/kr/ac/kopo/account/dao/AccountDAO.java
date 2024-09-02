package kr.ac.kopo.account.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.account.vo.StockHoldingVO;
import kr.ac.kopo.account.vo.TradingVO;
import kr.ac.kopo.util.MyBatisConfig;

public class AccountDAO {

	private SqlSession session;
	
	public AccountDAO() {
		session = new MyBatisConfig().getInstance();
		System.out.println("AccountDAO 접속 session : " + session);
	}
	
	public boolean selectOneDuplicatedNum(String accNum) {
		
		AccountVO accountVO = null;
		accountVO = session.selectOne("account.dao.AccountDAO.selectOneDuplicatedNum", accNum);
		
		if (accountVO == null) {
			return false;
		}
		return true;
		
	}
	
	public void insertNewAccount(AccountVO accountVO) {
		session.insert("account.dao.AccountDAO.insertNewAccount", accountVO);
		session.insert("account.dao.AccountDAO.insertNewAccountName", accountVO);
		session.commit();
	}
	
	public List<AccountVO> selectListOwnAccount(String memberId) {
		return session.selectList("account.dao.AccountDAO.selectListOwnAccount", memberId);
	}
	
	public AccountVO selectOneAccountInfo(String accountNum) {
		return session.selectOne("account.dao.AccountDAO.selectOneAccountInfo", accountNum);
	}
	
	public void insertNewTradingTransaction(TradingVO tradingVO) {
		
		try {
			//transaction
			session.getConnection().setAutoCommit(false);
			//잔금 업데이트 account
			Map<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("accountNum", tradingVO.getAccountNum());
			tmpMap.put("price", tradingVO.getStockPrice()*tradingVO.getStockCount() + "");
			if (tradingVO.getTradingType().equals("B")) {
				session.update("account.dao.AccountDAO.updateBalance", tmpMap);
			}
			
			session.insert("account.dao.AccountDAO.insertNewTrading", tradingVO);
			session.commit();
//			session.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			//트랜잭션 롤백
			session.rollback();
			e.printStackTrace();
		}
		
	}
	
	public List<TradingVO> selectListTradingVOByAccountNum(String accountNum) {
		return session.selectList("account.dao.AccountDAO.selectListTradingVOByAccountNum", accountNum);
	}
	
	public List<Map<String, Object>> selectListTradingList(String memberId) {
		return session.selectList("account.dao.AccountDAO.selectListTradingList", memberId);
	}
	// 매수 작업
	public void tradingCompleteWork(Map<String, Object> map) {
		try {
			//transaction
			session.getConnection().setAutoCommit(false);
			AccountVO tmp = session.selectOne("account.dao.AccountDAO.selectOneAccountInfo", (String)map.get("ACCOUNT_NUM"));

			// 거래중인거 map의 TRADING_CD로 TRADING에서 삭제
			BigDecimal bigD = (BigDecimal)map.get("TRADING_CD");
			int x = bigD.intValue();
			BigDecimal bbigD = (BigDecimal)map.get("STOCK_COUNT");
			long y = bbigD.longValue();
			BigDecimal bbbigD = (BigDecimal)map.get("STOCK_PRICE");
			long z = bbbigD.longValue();
			
			session.delete("account.dao.AccountDAO.deleteTradingByCd", x);
			
			// TRADING_HOLDING 테이블에 삽입
			session.insert("account.dao.AccountDAO.insertTradingHolding", map);
			
			// TRADING_COMPLETE 테이블에 삽입
			session.insert("account.dao.AccountDAO.insertTradingComplete", map);
			
			map.put("balance", tmp.getBalance());
			// ACCOUNT_HISTORY 테이블에 삽입
			session.insert("account.dao.AccountDAO.insertBuyAccountHistory", map);
			
			session.commit();
//			session.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			//트랜잭션 롤백
			session.rollback();
			e.printStackTrace();
		}
	}
	
	public void sellTradingCompleteWork(Map<String, Object> map) {
		try {
			//transaction
			session.getConnection().setAutoCommit(false);
			AccountVO tmp = session.selectOne("account.dao.AccountDAO.selectOneAccountInfo", (String)map.get("ACCOUNT_NUM"));

			// 거래중인거 map의 TRADING_CD로 TRADING에서 삭제
			BigDecimal bigD = (BigDecimal)map.get("TRADING_CD");
			int x = bigD.intValue();
			session.delete("account.dao.AccountDAO.deleteTradingByCd", x);
			System.out.println(map);
			// TRADING_HOLDING 테이블에서 삭제 또는 업데이트
			StockHoldingVO stockHoldingVO = session.selectOne("account.dao.AccountDAO.selectOneStockHoldingByAccNumAndItmsNm", map);
			BigDecimal bbigD = (BigDecimal)map.get("STOCK_COUNT");
			long y = bbigD.longValue();
			BigDecimal bbbigD = (BigDecimal)map.get("STOCK_PRICE");
			long z = bbbigD.longValue();
			if (stockHoldingVO.getStockCount() == y) {
				session.delete("account.dao.AccountDAO.deleteStockHoldingByAccNumAndItmsNm", map);
			} else {
				session.update("account.dao.AccountDAO.updateStockHoldingBySell", map);
			}
			//session.insert("account.dao.AccountDAO.insertTradingHolding", map);
			
			// TRADING_COMPLETE 테이블에 삽입
			session.insert("account.dao.AccountDAO.insertTradingComplete", map);
			
			// account에 보유금액 최신화
			session.update("account.dao.AccountDAO.updateAccountBalanceBySell", map);
			
			map.put("balance", tmp.getBalance() + y*z);
			// ACCOUNT_HISTORY 테이블에 삽입
			session.insert("account.dao.AccountDAO.insertSellAccountHistory", map);
			
			session.commit();
//			session.getConnection().setAutoCommit(true);
		} catch (Exception e) {
			//트랜잭션 롤백
			session.rollback();
			e.printStackTrace();
		}
	}
	
	public List<StockHoldingVO> selectListHoldingStockByAccountNum(String accountNum) {
		return session.selectList("account.dao.AccountDAO.selectListHoldingStockByAccountNum", accountNum);
	}
	
}
