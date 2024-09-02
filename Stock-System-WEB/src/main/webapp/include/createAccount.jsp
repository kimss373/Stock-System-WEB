<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 모의계좌개설 모달 -->
<div class="modal fade" id="modal1" aria-hidden="true" aria-labelledby="#exampleModalToggleLabel01" tabindex="-1">
  	<div class="modal-dialog modal-dialog-centered">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h1 class="modal-title fs-5" id="exampleModalToggleLabel01">주식계좌개설</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      		</div>
      	<div class="modal-body">
        <form action="/Stock-System-WEB/createAccountProcess.do" method="post">
		<div class="mb-3">
			<label for="accountName" class="form-label">계좌 이름</label>
			<input type="text" class="form-control" name="accountName" id="accountName" aria-describedby="basic-addon3 basic-addon4" placeholder="이름을 입력해주세요">
		</div>
		<div class="mb-3">
			<!-- <label for="accountPwd" class="form-label">초기 금액</label>
			<select class="form-select" aria-label="Default select example" name="balance">
				<option>100만원</option>
				<option>500만원</option>
				<option selected="selected">1000만원</option>
				<option>5000만원</option>
				<option>1억원</option>
			</select> -->
			<label for="accountPwd" class="form-label">계좌 비밀번호</label>
			<input type="password" class="form-control" name="accountPwd" id="accountPwd" aria-describedby="basic-addon3 basic-addon4">
		</div>
		<div class="mb-3">
			<label for="accountPwd" class="form-label">계좌 설명</label>
			<textarea class="form-control" name="accountDescribe" placeholder="설명을 입력해주세요.(선택사항)"></textarea>
		</div>
		<div class="mb-3 d-flex justify-content-end">
			<button class="btn btn-primary" type="submit">생성</button>
		</div>
	</form>
	</div>
    </div>
  </div>
</div>


