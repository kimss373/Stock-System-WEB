<%@page import="kr.ac.kopo.member.dao.MemberDAO"%>
<%@page import="kr.ac.kopo.vo.MemberVO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="kr.ac.kopo.stock.vo.StockVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	// loginUser를 세션에 갱신
	MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

	if (loginUser != null) {
		session.setAttribute("loginUser", loginUser);
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주식 차트 페이지</title>
    <link rel="stylesheet" href="/Stock-System-WEB/basicRightSideStyle.css">
    <link rel="stylesheet" href="/Stock-System-WEB/myStyles.css">
    <link rel="stylesheet" href="/Stock-System-WEB/rightSideStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/apexcharts/dist/apexcharts.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Dongle&family=Jua&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
	<script src="/Stock-System-WEB/js/jquery-3.7.1.min.js"></script>
	
<script>
    var dataFromJSP = <%= new Gson().toJson(request.getAttribute("stockList")) %>;
    var lastDayStock = <%= new Gson().toJson(request.getAttribute("lastDayStockVO")) %>;
    var last2DayClpr = ${requestScope.last2DayClpr};
    var nowLeftSideAccount = [];
    var sessionUsername = null;
    var nowBalance = 0;
    <%
    	if (loginUser != null) {
    %>
    		sessionUsername = "<%=loginUser.getId()%>";
    <%
    	}
    %>
</script>
</head>
<body style="height:939px;">
    <div class="container jua-regular" style="width:100%; max-width:100%; max-height:100%;">
        <header>
            <div class="logo"><img class="img-logo" src="/Stock-System-WEB/img/seongsuSquare.png" />&nbsp;KOM증권</div>
            <div class="search-bar">
            	<form action="/Stock-System-WEB/getOneStock.do" method="post" autocomplete="off">
	                <input type="text" name="itmsNm" id="searchInput" placeholder="종목 검색">
	                <button class="btn btn-secondary">검색</button>
            	</form>
                <div id="dropdown" class="dropdown-content"></div>
            </div>
            
            <c:if test="${ sessionScope.loginUser eq null }">
            <!-- 로그인 모달 부분 시작 -->
            	<jsp:include page="/include/loginInclude.jsp"/>
            <!-- 로그인 모달 부분 끝 -->
			</c:if>
			<c:if test="${ sessionScope.loginUser ne null }">
            <!-- 로그인 하면 나오는 버튼----------------------------------------------------------------------- -->
            <div class="chart-header dropdown">
            	<button class="btn btn-success me-4 dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Family Site</button>
            	<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				    <li><a class="dropdown-item" href="http://172.31.9.179:8081/banking/bingo/banking.do">KOM은행</a></li>
				    <li><a class="dropdown-item" href="http://172.31.9.174:8080/KOMcard/home.do">KOM카드</a></li>
				    <li><a class="dropdown-item" href="#">KOM증권</a></li>
				    <li><a class="dropdown-item" href="http://172.31.9.182/kom_finance_project/index.jsp">KOM금융그룹</a></li>
				</ul>
            	<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><img class="img-fluid" src="/Stock-System-WEB/img/profile.jpg" /></a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					    <li><a class="dropdown-item" href="#">Action</a></li>
					    <li><a class="dropdown-item" href="#">내 정보 변경</a></li>
					    <li><a class="dropdown-item" href="/Stock-System-WEB/logoutProcess.do">로그아웃</a></li>
					</ul>

            </div>
            <!-- 로그인 하면 나오는 버튼 끝 -->
            </c:if>
        </header>
        <div class="main">
            <aside class="sidebar">
           		<div class="d-flex justify-content-between" style="border:solid black; border-width: 0px 0px 1px 0px; margin-botton:10px;">
            		<select id="selectAccountLeftSide" class="form-select" aria-label="Default select example" onchange="selectAccountForHoldingStock()">
            			<option value="0">----</option>
            			<c:forEach items="${ requestScope.accountList }" var="account">
            				<option value="${account.accountNum }">${account.accountName }</option>
            			</c:forEach>
            		</select>
            	</div>
            	<div id="totalAssets">
            	</div>
            	<div id="leftSideGround">
	            	계좌를 선택하세요
                </div>
            </aside>
            <section class="content">
                <div class="chart-header">
                    <div class="chart-title" id="chartHead1">${ requestScope.lastDayStockVO.itmsNm }(${requestScope.lastDayStockVO.mrktCtg}) ${requestScope.lastDayStockVO.clpr}</div>                    <div class="chart-subtitle" id="chartHead2">${requestScope.lastDayStockVO.fltRt }%</div>
                    <div class="chart-info" id="chartHead3">거래량 ${requestScope.lastDayStockVO.trqu } 거래대금 ${requestScope.stockList[0].trprc }</div>
                    <div class="header-right">
                	관심
                	<button type="button" class="btn btn-danger" id="bttn" style="background-color:#ed2926;">매수</button>
                	<button type="button" class="btn btn-primary" id="bttn1">매도</button>
            		</div>
                </div>
                <div class="chart">
                    <div id="chart"></div>
                </div>
                
                <div class="volume">
                    거래량 <fmt:formatNumber value="${requestScope.lastDayStockVO.trqu }" pattern="#,###" /> (<fmt:formatNumber value="${requestScope.trquRate }" pattern="#,###" />%) / 상장주식수 <fmt:formatNumber value="${requestScope.lastDayStockVO.lstgStCnt }" pattern="#,###" /> / 시가총액 <fmt:formatNumber value="${requestScope.lastDayStockVO.mrktTotAmt }" pattern="#,###" />
                </div>
            </section>
            <aside class="right-sidebar">
		    	<div class="buy-sidebar" id="buy-sidebar">
		        	<!-- 오른쪽 나타나는 매수/매도 하는 사이드바 내용 들어가야 함 -->
		        	<div class="order-page">
				        <header>
				            <c:if test="${ sessionScope.loginUser ne null }">
					            <div class="account-info">
					                <select class="form-select" id="selectAccountRightSide" aria-label="Default select example" onchange="selectAccount()">
					                	<option value="0">----</option>
				            			<c:forEach items="${ requestScope.accountList }" var="account">
				            				<option value="${account.accountNum }">${account.accountName }</option>
				            			</c:forEach>		
			            			</select>
					            </div>
					            <!-- 
					            	모의계좌 개설 modal 들어갈 자리
					             --><button class="btn btn-success" data-bs-target="#modal1" data-bs-toggle="modal">주식계좌 생성</button>
					             	
				             </c:if>
				        </header>
				        
				        <div class="stock-info">
				            <div class="stock-name" id="buyName">대원전선</div>
				            <div class="stock-code" id="buyInfo">006340 코스피</div>
				            <div class="stock-prices">
				                <div class="price" id="1"></div>
				                <div class="volume" id="buyPrice">90,252</div>
				            </div>
				        </div>
						<form method="post" onsubmit="return buyOrSellStock()" id="buyOrSellAction">
							<input type="hidden" name="accountNum" value="0" id="formAccountNum"/>
							<input type="hidden" name="srtnCd" value="${ requestScope.lastDayStockVO.srtnCd }"/>
							<input type="hidden" name="buyOrSell" value="buy" id="buyOrSell"/>
							<input type="hidden" name="itmsNm" value="${lastDayStockVO.itmsNm}" id="buyOrSell"/>
					        <div class="order-section">
					            <div class="order-type">
					                <button type="button" class="order-button active" id="buyButton">매수</button>
					                <button type="button" class="order-button" id="sellButton">매도</button>
					            </div>
					            
					            <div class="order-form">
					                <div class="price-section">
					                    <label for="price">가격</label>
					                    <select id="price-type">
					                        <option value="지정가">지정가</option>
					                        <option value="시장가">시장가</option>
					                    </select>
					                    <input type="number" name="stockPrice" id="price" value="${requestScope.lastDayStockVO.clpr }"  min="0">원
					                </div>
					                
					                <div class="amount-section">
					                	<div class="d-flex justify-content-between">
					                    <label for="amount">수량</label>
					                	<label for="amount">최대 <span id="showMaxAmount">0</span>주</label>
					                	</div>
					                    <input type="number" name="stockCount" id="amount" value="0" min="0">주
					                    <div class="amount-buttons">
					                        <button type="button" onclick="buyWithAutoCalc(10)">10%</button>
					                        <button type="button" onclick="buyWithAutoCalc(4)">25%</button>
					                        <button type="button" onclick="buyWithAutoCalc(2)">50%</button>
					                        <button type="button" onclick="buyWithAutoCalc(1)">100%</button>
					                    </div>
					                </div>
					                <div class="total-section">
					                	<div class="total-label">보유금액</div>
					                    <div class="total-value" id="nowBalance">0</div>
					                </div>
									
					                <div class="total-section">
					                    <div class="total-label">주문총액</div>
					                    <div class="total-value" id="totalValue">0</div>
					                </div>
									<c:if test="${ sessionScope.loginUser eq null }">
							            <!-- 로그인 모달 부분 시작 -->
							            <jsp:include page="/include/loginIncludeForRight.jsp"/>
							            <!-- 로그인 모달 부분 끝 -->
									</c:if>
									<c:if test="${ sessionScope.loginUser ne null }">
					                	<button type="button" class="submit-button" onclick="buyOrSellActionSubmit()">매수</button>
					                </c:if>
					            </div>
					        </div>
						</form>

				        <div class="order-history" id="order-history">
				            <div class="history-label">주문내역</div>
				            <div class="history-content">주문 내역이 없습니다.</div>
				        </div>
				    </div>
		        </div>
		        
		        
				<div class="container ">
        <div class="tabs d-flex justify-content-evenly">
            <button class="tablinks" onclick="openTab(event, 'features')" id="defaultOpen">특징종목</button>
            <button class="tablinks" onclick="openTab(event, 'community')">커뮤니티</button>
        </div>

        <div id="features" class="tabcontent">
            <div class="subtabs d-flex justify-content-center">
                <button class="subtablinks" onclick="openSubTab(event, 'rising')" id="defaultSubOpen">상승률</button>
                <button class="subtablinks" onclick="openSubTab(event, 'falling')">하락률</button>
            </div>
            <div id="rising" class="subtabcontent">
                <!-- 상승률 내용 -->
                <c:set var="count" value="15" />
				<c:set var="tableIndex" value="1" />
				<c:forEach var="item" items="${requestScope.upList}" varStatus="status">
				    <c:if test="${status.index % 5 == 0}">
				        <table class="table-separator">
				    </c:if>
				        <tr onclick="location.href='toHome.jsp?itmsNm=${item.itmsNm}'" style="cursor: pointer;">
				            <td>${status.index + 1}</td>
				            <td>${item.itmsNm}</td>
				            <td>${item.clpr}</td>
				            <td class="${item.fltRt > 0 ? 'up' : 'down'}">${item.fltRt > 0 ? '+' : ''}${item.fltRt}%</td>
				        </tr>
				    <c:if test="${(status.index + 1) % 5 == 0 || status.index == count - 1}">
				        </table>
				        <c:set var="tableIndex" value="${tableIndex + 1}" />
				    </c:if>
				</c:forEach>
                
            </div>
            <div id="falling" class="subtabcontent">
                <!-- 하락률 내용 -->
                <c:set var="count" value="15" />
				<c:set var="tableIndex" value="1" />
				<c:forEach var="item" items="${requestScope.downList}" varStatus="status">
				    <c:if test="${status.index % 5 == 0}">
				        <table class="table-separator">
				    </c:if>
				        <tr onclick="location.href='toHome.jsp?itmsNm=${item.itmsNm}'" style="cursor: pointer;">
				            <td>${status.index + 1}</td>
				            <td>${item.itmsNm}</td>
				            <td>${item.clpr}</td>
				            <td class="${item.fltRt > 0 ? 'up' : 'down'}">${item.fltRt > 0 ? '+' : ''}${item.fltRt}%</td>
				        </tr>
				    <c:if test="${(status.index + 1) % 5 == 0 || status.index == count - 1}">
				        </table>
				        <c:set var="tableIndex" value="${tableIndex + 1}" />
				    </c:if>
				</c:forEach>
            </div>
        </div>

        <div id="community" class="tabcontent">
            <!-- 커뮤니티 내용 -->
            <p>커뮤니티 내용이 여기에 표시됩니다.</p>
        </div>
    </div>
   		</aside>
	</div>
        <footer>
            <div class="footer-content">
                국내 증시 2,790.98 -0.59% / 코스닥 850.58 -0.81% / 해외 증시 S&P500 5,473.17 -0.25%
            </div>
        </footer>
        <jsp:include page="/include/createAccount.jsp"/>
    </div>


<script src="/Stock-System-WEB/utility.js"></script>
<script src="/Stock-System-WEB/leftSideJS.js"></script>
<script src="/Stock-System-WEB/memberJS.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="/Stock-System-WEB/stockChart.js"></script>
<script src="/Stock-System-WEB/rightSideJS.js"></script>
<script src="/Stock-System-WEB/basicRightSideJS.js"></script>
</body>
</html>
