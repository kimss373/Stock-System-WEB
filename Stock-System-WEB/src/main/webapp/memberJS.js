/**
 * 
 */
$().ready(function() {

	var memberIdPermission = false;
	
	$("#memberId").keyup(function(){
		
		memberIdPermission = false;
		
	});
	
	$("#btnOverlapped").click(function(){
		
	    var memberId = $("#memberId").val();
	   
	    if (memberId == ''){
	   		alert("ID를 입력하세요");
	   		return;
	    }
	   
	    $.ajax({
	       type : "get",
	       url  : "/Stock-System-WEB/member/checkDuplicatedId.jsp?memberId=" + memberId,
	       success : function (data){
	    	   data = JSON.parse(data);
	          if (data.getId == "yes"){
				  alert("사용할 수 있는 ID입니다.");
				  memberIdPermission = true;
	          }
	          else {
	          	  alert("이미 존재하는 ID입니다.");
	          	  memberIdPermission = false;
	          }
	       }
	    });
	    
	 });
	
	
	$("#submitBtn").on("click", function(){
		var num = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
		
		var hp = $("#hp").val();
		
		for (var i=0 ; i<hp.length ; i++) {
			if (!(hp.charAt(i) in num)){
				alert("연락처를 양식에 맞게 입력하세요");
				$("#hp").focus();
				return false;
			}
		}
		
		if ($("#passwd").val() != $("#confirmPasswd").val()) {
			alert("비밀번호를 확인하세요.");
			$("#passwd").focus();
			return false;
		}
		
		if (!memberIdPermission) {
			alert("아이디 중복확인 하세요");
			$("#memberId").focus();
			return false;
		}
		
		var form1 = $("#registerForm").serialize();
		
		$.ajax({
	       type : "post",
	       url  : "/Stock-System-WEB/member/registerMember.jsp",
	       data: form1,
	       success : function (data){
	    	   data = JSON.parse(data);
			   alert("회원가입 성공");
			   location.reload(true);
	       }
	       
	    });
		
	});
	
});
