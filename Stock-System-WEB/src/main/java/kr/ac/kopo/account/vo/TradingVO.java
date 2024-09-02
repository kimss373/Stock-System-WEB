package kr.ac.kopo.account.vo;

public class TradingVO {

	private long tradingCd;
	private String accountNum;
	private String srtnCd;
	private long stockPrice;
	private long stockCount;
	private String tradingType;
	
	
	public TradingVO() {
		
	}
	
	public TradingVO(String accountNum, String srtnCd, long stockPrice, long stockCount) {
		this.accountNum = accountNum;
		this.srtnCd = srtnCd;
		this.stockPrice = stockPrice;
		this.stockCount = stockCount;
	}

	public long getTradingCd() {
		return tradingCd;
	}

	public void setTradingCd(long tradingCd) {
		this.tradingCd = tradingCd;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getSrtnCd() {
		return srtnCd;
	}

	public void setSrtnCd(String srtnCd) {
		this.srtnCd = srtnCd;
	}

	public long getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(long stockPrice) {
		this.stockPrice = stockPrice;
	}

	public long getStockCount() {
		return stockCount;
	}

	public void setStockCount(long stockCount) {
		this.stockCount = stockCount;
	}

	public String getTradingType() {
		return tradingType;
	}

	public void setTradingType(String tradingType) {
		this.tradingType = tradingType;
	}

	@Override
	public String toString() {
		return "TradingVO [tradingCd=" + tradingCd + ", accountNum=" + accountNum + ", srtnCd=" + srtnCd
				+ ", stockPrice=" + stockPrice + ", stockCount=" + stockCount + ", tradingType=" + tradingType + "]";
	}
		
}
