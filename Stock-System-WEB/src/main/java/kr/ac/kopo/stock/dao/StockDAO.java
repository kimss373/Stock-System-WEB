package kr.ac.kopo.stock.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.stock.vo.StockVO;
import kr.ac.kopo.util.MyBatisConfig;

public class StockDAO {

	private SqlSession session;
	
	public StockDAO() {
		session = new MyBatisConfig().getInstance();
		System.out.println("StockDAO Á¢¼Ó session : " + session);
	}
	
	public List<StockVO> selectAllRiseRateChart() {
		List<StockVO> list = session.selectList("stock.dao.StockDAO.selectAllRiseRateChart");
		return list;
	}
	
	public List<StockVO> selectAllFallRateChart() {
		List<StockVO> list = session.selectList("stock.dao.StockDAO.selectAllFallRateChart");
		return list;
	}
	
	public List<StockVO> selectAllStock(){
		return session.selectList("stock.dao.StockDAO.selectAllStock");
	}
	
	public List<String> selectALlStockName(String query){
		return session.selectList("stock.dao.StockDAO.selectAllStockName", query);
	}
	
	public List<StockVO> selectListStockByName(String itmsNm) {
		
		return session.selectList("stock.dao.StockDAO.selectListStockByName", itmsNm);
	}
	
}
