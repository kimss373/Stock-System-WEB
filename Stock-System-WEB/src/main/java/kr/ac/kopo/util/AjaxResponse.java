package kr.ac.kopo.util;

import java.util.Map;

import kr.ac.kopo.stock.vo.StockVO;

public class AjaxResponse {

	private Map<String, StockVO> stockMap;
	private boolean toggleSign;
	
	// Constructor
    public AjaxResponse(Map<String, StockVO> stockMap, boolean toggleSign) {
        this.stockMap = stockMap;
        this.toggleSign = toggleSign;
    }

    // Getters and Setters
    public Map<String, StockVO> getStockMap() {
        return stockMap;
    }

    public void setStockMap(Map<String, StockVO> stockMap) {
        this.stockMap = stockMap;
    }

    public boolean isToggleSign() {
        return toggleSign;
    }

    public void setToggleSign(boolean toggleSign) {
        this.toggleSign = toggleSign;
    }
	
}
