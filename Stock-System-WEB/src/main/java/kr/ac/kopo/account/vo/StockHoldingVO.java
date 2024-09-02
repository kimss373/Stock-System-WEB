package kr.ac.kopo.account.vo;

public class StockHoldingVO {

	private long stockHoldingCd;
	private String accountNum;
	private String itmsNm;
	private String srtnCd;
	private long stockPrice;
	private long stockCount;
	private double stockMean;
	
	
	
	public String getSrtnCd() {
		return srtnCd;
	}

	public void setSrtnCd(String srtnCd) {
		this.srtnCd = srtnCd;
	}

	public double getStockMean() {
		return stockMean;
	}

	public void setStockMean(double stockMean) {
		this.stockMean = stockMean;
	}

	public StockHoldingVO() {
		
	}
	
	public StockHoldingVO(String accountNum, String itmsNm, long stockPrice, long stockCount) {
		this.accountNum = accountNum;
		this.itmsNm = itmsNm;
		this.stockPrice = stockPrice;
		this.stockCount = stockCount;
	}
	public StockHoldingVO(String accountNum, String itmsNm, long stockPrice, long stockCount, double stockMean) {
		this.accountNum = accountNum;
		this.itmsNm = itmsNm;
		this.stockPrice = stockPrice;
		this.stockCount = stockCount;
		this.stockMean = stockMean;
	}
	public long getStockHoldingCd() {
		return stockHoldingCd;
	}
	public void setStockHoldingCd(long stockHoldingCd) {
		this.stockHoldingCd = stockHoldingCd;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getItmsNm() {
		return itmsNm;
	}
	public void setItmsNm(String itmsNm) {
		this.itmsNm = itmsNm;
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
	@Override
	public String toString() {
		return "StockHoldingVO [stockHoldingCd=" + stockHoldingCd + ", accountNum=" + accountNum + ", itmsNm=" + itmsNm
				+ ", srtnCd=" + srtnCd + ", stockPrice=" + stockPrice + ", stockCount=" + stockCount + ", stockMean="
				+ stockMean + "]";
	}
	
	
	
}
