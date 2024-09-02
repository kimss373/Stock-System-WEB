package kr.ac.kopo.stock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.framework.Controller;
import kr.ac.kopo.stock.dao.StockDAO;
import kr.ac.kopo.stock.vo.StockVO;

public class StockController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//PageContext pageContext = new PageContext();
		//pageContext.setAttribute("mailList", list);
		return "/index.jsp";
	}
	
}
