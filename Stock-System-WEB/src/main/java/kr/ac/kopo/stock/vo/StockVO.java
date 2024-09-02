package kr.ac.kopo.stock.vo;

public class StockVO {

	private int stockCd;
	private String basDt;
	private String srtnCd;
	private String isinCd;
	private String itmsNm;
	private String mrktCtg;
	private long clpr;
	private long vs;
	private double fltRt;
	private long mkp;
	private long hipr;
	private long lopr;
	private long trqu;
	private long trprc;
	private long lstgStCnt;
	private long mrktTotAmt;
	public int getStockCd() {
		return stockCd;
	}
	public void setStockCd(int stockCd) {
		this.stockCd = stockCd;
	}
	public String getBasDt() {
		return basDt;
	}
	public void setBasDt(String basDt) {
		this.basDt = basDt;
	}
	public String getSrtnCd() {
		return srtnCd;
	}
	public void setSrtnCd(String srtnCd) {
		this.srtnCd = srtnCd;
	}
	public String getIsinCd() {
		return isinCd;
	}
	public void setIsinCd(String isinCd) {
		this.isinCd = isinCd;
	}
	public String getItmsNm() {
		return itmsNm;
	}
	public void setItmsNm(String itmsNm) {
		this.itmsNm = itmsNm;
	}
	public String getMrktCtg() {
		return mrktCtg;
	}
	public void setMrktCtg(String mrktCtg) {
		this.mrktCtg = mrktCtg;
	}
	public long getClpr() {
		return clpr;
	}
	public void setClpr(long clpr) {
		this.clpr = clpr;
	}
	public long getVs() {
		return vs;
	}
	public void setVs(long vs) {
		this.vs = vs;
	}
	public double getFltRt() {
		return fltRt;
	}
	public void setFltRt(double fltRt) {
		this.fltRt = fltRt;
	}
	public long getMkp() {
		return mkp;
	}
	public void setMkp(long mkp) {
		this.mkp = mkp;
	}
	public long getHipr() {
		return hipr;
	}
	public void setHipr(long hipr) {
		this.hipr = hipr;
	}
	public long getLopr() {
		return lopr;
	}
	public void setLopr(long lopr) {
		this.lopr = lopr;
	}
	public long getTrqu() {
		return trqu;
	}
	public void setTrqu(long trqu) {
		this.trqu = trqu;
	}
	public long getTrprc() {
		return trprc;
	}
	public void setTrprc(long trprc) {
		this.trprc = trprc;
	}
	public long getLstgStCnt() {
		return lstgStCnt;
	}
	public void setLstgStCnt(long lstgStCnt) {
		this.lstgStCnt = lstgStCnt;
	}
	public long getMrktTotAmt() {
		return mrktTotAmt;
	}
	public void setMrktTotAmt(long mrktTotAmt) {
		this.mrktTotAmt = mrktTotAmt;
	}
	@Override
	public String toString() {
		return "StockVO [stockCd=" + stockCd + ", basDt=" + basDt + ", srtnCd=" + srtnCd + ", isinCd=" + isinCd
				+ ", itmsNm=" + itmsNm + ", mrktCtg=" + mrktCtg + ", clpr=" + clpr + ", vs=" + vs + ", fltRt=" + fltRt
				+ ", mkp=" + mkp + ", hipr=" + hipr + ", lopr=" + lopr + ", trqu=" + trqu + ", trprc=" + trprc
				+ ", lstgStCnt=" + lstgStCnt + ", mrktTotAmt=" + mrktTotAmt + "]";
	}
	
}
