<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인관련 시작 -->
			
            <div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
            
            	<!-- Family Site 공간 -->
  				<div class="modal-dialog modal-dialog-centered">
    				<div class="modal-content">
      					<div class="modal-header">
        					<h1 class="modal-title fs-5" id="exampleModalToggleLabel">로그인</h1>
        					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      					</div>
      					<div class="modal-body">
	    					<form action="/Stock-System-WEB/loginProcess.do" method="post">
	        					<div class="mb-3">
	  								<label for="basic-url" class="form-label">아이디</label>
	  								<input type="text" class="form-control" name="id" id="basic-url" aria-describedby="basic-addon3 basic-addon4">
	  							</div>
	  							<div class="mb-3">
	  								<label for="basic-url" class="form-label">비밀번호</label>
	  								<input type="password" class="form-control" name="pwd" id="basic-url" aria-describedby="basic-addon3 basic-addon4">
	  							</div>
	  							<div class="mb-3 d-flex justify-content-end">
		        					<button class="btn btn-primary" type="submit">로그인</button>
	  							</div>
	    					</form>
      					</div>
      					<div class="modal-footer d-flex justify-content-between">
      						<a href="#" data-bs-target="#exampleModalToggle2" data-bs-toggle="modal">회원가입</a>
 							<a href="#" data-bs-target="#exampleModalToggle3" data-bs-toggle="modal">아이디 찾기</a>
							<a href="#" data-bs-target="#exampleModalToggle4" data-bs-toggle="modal">비밀번호 찾기</a>
				        </div>
				    </div>
				</div>
			</div>
			<div class="modal fade" id="exampleModalToggle2" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
			    <div class="modal-dialog modal-dialog-centered">
			    	<div class="modal-content">
			      		<div class="modal-header">
			        		<h1 class="modal-title fs-5" id="exampleModalToggleLabel2">회원가입</h1>
			        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      		</div>
				      	<div class="modal-body">
				        	<!-- Registration form-->
	                        <form method="post" id="registerForm">
	                            <!-- Form Row-->
	                            <div class="form-row">
	                                <div class="col-md-6">
	                                    <!-- Form Group (name)-->
	                                    <div class="form-group">
	                                        <label class="small mb-1" for="inputFirstName">이름</label>
	                                        <input class="form-control" name="name" type="text" />
	                                    </div>
	                                </div>
	                            </div>
	                            <!-- Form Row-->
	                            <div class="form-row">
	                                <div class="col-md-6">
	                                    <!-- Form Group (memberId)-->
	                                    <div class="form-group">
	                                        <label class="small mb-1" for="inputFirstName">아이디</label>
	                                        <input class="form-control" name="memberId" id="memberId" type="text" />
	                                        <button class="btn btn-danger" type="button" id="btnOverlapped">중복확인</button>
	                                    </div>
	                                </div>
	                            </div>
	                            <!-- Form Row    -->
	                            <div class="form-row d-flex justify-content-between">
	                                <div class="col-md-6">
	                                    <!-- Form Group (passwd)-->
	                                    <div class="form-group">
	                                        <label class="small mb-1" for="inputPassword">비밀번호</label>
	                                        <input class="form-control" id="passwd" name="pwd" type="password"/>
	                                    </div>
	                                </div>
	                                <div class="col-md-6">
	                                    <!-- Form Group (confirm passwd)-->
	                                    <div class="form-group">
	                                        <label class="small mb-1" for="inputConfirmPassword">비밀번호 확인</label>
	                                        <input class="form-control" id="confirmPasswd" type="password"  />
	                                    </div>
	                                </div>
	                            </div>
	                            <!-- Form Row-->
	                            <div class="form-row">
	                                <div class="col-md-6">
	                                    <!-- Form Group (hp)-->
	                                    <div class="form-group">
	                                        <label class="small mb-1" for="inputFirstName">연락처</label>
	                                        <input class="form-control" name="hp" id="hp" type="text" placeholder="- 없이 입력하세요"/>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="form-row d-flex justify-content-between" style="align-items:center;">
	                                <div class="col-md-5">
	                                    <!-- Form Group (hp)-->
	                                    <div class="form-group">
	                                        <label class="small mb-1" for="inputFirstName">주민등록번호 앞자리</label>
	                                        <input class="form-control" name="personalNum1" id="personalNum1" type="text"/>
	                                    </div>
	                                </div>
	                                <div class="d-flex justify-content-center" style="align-items:center; height:100%;">
	                                        <span style="padding:0 10px;">-</span>
	                                </div>
	                                <div class="col-md-5">
	                                    <!-- Form Group (personalNum)-->
	                                    <div class="form-group">
	                                        <label class="small mb-1" for="inputFirstName">뒷자리</label>
	                                        <input class="form-control" name="personalNum2" id="personalNum2" type="password"/>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="form-row d-flex justify-content-between" style="align-items:center;">
	                                <div class="col-md-12">
		                                <div class="form-group">
							                <h3>회원가입 안내사항</h3>
							                <p style="color:black">
							                    KOM 금융그룹에 가입하면 KOM은행, KOM카드, KOM증권에 회원 정보를 공유합니다.
							                    회원 정보는 안전하게 관리되며, 금융상품 및 서비스 제공을 위해 사용됩니다.
							                    가입 시 제공된 정보는 KOM 금융그룹의 모든 계열사에서 통합 관리되며, 최적의 금융 혜택을 제공하기 위해 활용됩니다.
							                    회원님의 정보는 관련 법규에 따라 엄격하게 보호되며, 타사와는 절대 공유되지 않습니다.
							                    다양한 금융 서비스와 혜택을 경험하시기 위해 KOM 금융그룹의 회원이 되어 주세요.
							                    회원님의 신뢰를 바탕으로 더 나은 금융 서비스를 제공할 것을 약속드립니다.
							                </p>
							                <label class="checkbox-container"  style="color:black">
							                    <input type="checkbox" id="terms" name="terms" required>
							                    <span class="checkmark"></span>
							                    회원가입 안내사항을 읽었으며, 이에 동의합니다.
							                </label>
							            </div>
						            </div>
						        </div>
	                            <!-- Form Row-->
	                            <!-- Form Group (create account submit)-->
	                            <div class="form-group mt-4 mb-0"><button class="btn btn-primary btn-block" type="button" id="submitBtn">회원가입</button></div>
	                        </form>
				      	</div>
				      	<div class="modal-footer">
				        	<button class="btn btn-primary" data-bs-target="#exampleModalToggle" data-bs-toggle="modal">Back</button>
				      	</div>
			    	</div>
				</div>
			</div>
			<div>
			<button class="btn btn-success me-4 dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Family Site</button>
            	<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				    <li><a class="dropdown-item" href="http://172.31.9.179:8081/banking/bingo/banking.do">KOM은행</a></li>
				    <li><a class="dropdown-item" href="http://172.31.9.174:8080/KOMcard/home.do">KOM카드</a></li>
				    <li><a class="dropdown-item" href="#">KOM증권</a></li>
				    <li><a class="dropdown-item" href="http://172.31.9.182:8080/kom_finance_project/index.jsp">KOM금융그룹</a></li>
				</ul>
			<button type="button" class="btn btn-primary" data-bs-target="#exampleModalToggle" data-bs-toggle="modal">로그인</button>
			</div>
			<!-- 로그인관련 끝 -->