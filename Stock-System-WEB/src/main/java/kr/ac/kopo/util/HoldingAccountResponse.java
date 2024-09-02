package kr.ac.kopo.util;

import java.util.List;

import kr.ac.kopo.account.vo.StockHoldingVO;

public class HoldingAccountResponse {

	private List<StockHoldingVO> holdings;
	private long balance;
	public List<StockHoldingVO> getHoldings() {
		return holdings;
	}
	public void setHoldings(List<StockHoldingVO> holdings) {
		this.holdings = holdings;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public HoldingAccountResponse(List<StockHoldingVO> holdings, long balance) {
		this.holdings = holdings;
		this.balance = balance;
	}
	
	
}
