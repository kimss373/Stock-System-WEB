package kr.ac.kopo.account.controller;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.account.dao.AccountDAO;
import kr.ac.kopo.account.vo.AccountVO;
import kr.ac.kopo.framework.Controller;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.vo.MemberVO;

public class CreateAccountProcessController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		String accountNum = createNewAccountNum();
		String memberId = loginUser.getId(); 
		String accountName = request.getParameter("accountName");
		String accountDescribe = request.getParameter("accountDescribe");
		//String getBalance = request.getParameter("balance");
		long balance = 0;
		String accountPwd = request.getParameter("accountPwd");
//		switch(getBalance) {
//		case "100만원":
//			balance = 1000000;
//			break;
//		case "500만원":
//			balance = 5000000;
//			break;
//		case "1000만원":
//			balance = 10000000;
//			break;
//		case "5000만원":
//			balance = 50000000;
//			break;
//		case "1억원":
//			balance = 100000000;
//			break;
//		}
		
		AccountVO accountVO = new AccountVO(accountNum, memberId, accountName, accountDescribe, balance);
		accountVO.setAccountPwd(accountPwd);
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.insertNewAccount(accountVO);
		return "/toHome.jsp";
	}

	private String createNewAccountNum() {
		Random random = new Random();
		AccountDAO accountDAO = new AccountDAO();
		
		while (true) {
			StringBuilder randomNumber = new StringBuilder();
			//주식 계좌 개설 101(지점)003(주식)99(주식)
			randomNumber.append("10100399");
			// Generate 14 random digits
			for (int i = 0; i < 6; i++) {
				int digit = random.nextInt(10); // Generate a random digit between 0 and 9
				randomNumber.append(digit);
			}
			
			// Insert hyphens at the appropriate positions
			randomNumber.insert(6, '-');
			randomNumber.insert(9, '-');
			
			if (accountDAO.selectOneDuplicatedNum(randomNumber.toString())) {
				continue;
			}
			
			return randomNumber.toString();
			
		}
	}
	
	
}
