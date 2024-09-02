import kr.ac.kopo.stock.dao.StockDAO;

public class MybatisTest {

	public static void main(String[] args) {
		
		StockDAO stockDAO = new StockDAO();
		
		stockDAO.selectAllRiseRateChart();
		
	}

}
