package kr.ac.kopo.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.stock.vo.StockVO;

public class UpdateStocksPrice {

	private static Map<String, StockVO> map = null;
	private SqlSession session;
	
	public UpdateStocksPrice() {
		session = new MyBatisConfig().getInstance();
		System.out.println("UpdateStocksPrice Á¢¼Ó session : " + session);
		
		if (map == null) {
			map = new HashMap<>();
			List<StockVO> getList = session.selectList("stock.dao.StockDAO.getStockListLastBas");
			
			for (int i = 0; i < getList.size(); i++) {
				map.put(getList.get(i).getItmsNm(), getList.get(i));
			}
			
		}
		
	}
	
	public void changeStockPriceRandom() {
		for (String stockItmsNm : map.keySet()) {
			StockVO nowStock = map.get(stockItmsNm);
			long hogaUnit = getHogaUnit(nowStock.getClpr());
			long direction = Math.random() < 0.5 ? -1 : 1;
			long newPrice = nowStock.getClpr() + (hogaUnit * direction);
			long min = Math.min(nowStock.getLopr(), nowStock.getHipr());
			long max = Math.max(nowStock.getLopr(), nowStock.getHipr());
			if (newPrice < min) newPrice = min;
			if (newPrice > max) newPrice = max;
			nowStock.setClpr(newPrice);
		}
	}
	
	public Map<String, StockVO> getAllStockMap(){
		return map;
	}
	
	private long getHogaUnit(long price) {
		if (price < 1000) return 1;
	    if (price < 5000) return 5;
	    if (price < 10000) return 10;
	    if (price < 50000) return 50;
	    if (price < 100000) return 100;
	    if (price < 500000) return 500;
	    return 1000;
	}
	
}
