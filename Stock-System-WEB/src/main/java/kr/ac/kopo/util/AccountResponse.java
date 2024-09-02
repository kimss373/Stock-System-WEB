package kr.ac.kopo.util;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.account.vo.StockHoldingVO;
import kr.ac.kopo.account.vo.TradingVO;

public class AccountResponse {
	private AccountVO account;
    private List<TradingVO> tradings;
    private Map<String, StockHoldingVO> holdingMap;

    public AccountResponse(AccountVO account, List<TradingVO> tradings, Map<String, StockHoldingVO> holdingMap) {
        this.account = account;
        this.tradings = tradings;
        this.holdingMap = holdingMap;
    }

    
    public Map<String, StockHoldingVO> getHoldings() {
		return holdingMap;
	}


	public void setHoldings(Map<String, StockHoldingVO> holdingMap) {
		this.holdingMap = holdingMap;
	}


	// Getters and Setters
    public AccountVO getAccount() {
        return account;
    }

    public void setAccount(AccountVO account) {
        this.account = account;
    }

    public List<TradingVO> getTradings() {
        return tradings;
    }

    public void setTradings(List<TradingVO> tradings) {
        this.tradings = tradings;
    }
}
