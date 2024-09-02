/**
 * 
 */

function selectAccountForHoldingStock(){
	let opt = document.getElementById('selectAccountLeftSide');
	let optVal = opt.options[opt.selectedIndex].value;
	if (optVal == '0') {
		document.getElementById('leftSideGround').innerHTML = '계좌를 선택하세요';
		return;
	}
	
	$.ajax({
        url: './ajax/getOneAccountHoldingInfo.jsp',
        method: 'GET',
        data: { accountNum: optVal },
        dataType: 'json', // JSON 형식으로 응답을 받음
        success: function(response) {
			let data = response.holdings;
			nowLeftSideAccount = data;
			nowBalance = response.balance;
			if (data.length == 0) {
				document.getElementById('leftSideGround').innerHTML ='<div class="d-flex justify-content-between" style="border:solid black; border-width: 0px 0px 1px 0px; margin-botton:10px;"><span>보유 주식이 없습니다.</span></div>'
			} else {
				let useHtml = '<div class="d-flex justify-content-between" style="border:solid black; border-width: 0px 0px 1px 0px; margin-botton:10px;"><span style="float:left">평가수익금</span><span style="float:right" id="evaluationProceeds">원</span></div><br><div class="list-group watchlist">'
				for (let i = 0; i < data.length; i++) {
					useHtml += '<a href="toHome.jsp?itmsNm=' + data[i].itmsNm + '" class="list-group-item list-group-item-action" aria-current="true"><div class="d-flex w-100 justify-content-between"><h5 class="mb-1">' + data[i].itmsNm + '</h5><small id="' + data[i].itmsNm + 'price' + i + '"></small></div><div class="d-flex w-100 justify-content-between"><small class="mb-1">보유</small><span id="' + data[i].itmsNm + 'totalPrice' + i + '" ></span>원<small>' + data[i].stockCount.toLocaleString() + '주</small></div><div class="d-flex w-100 justify-content-between"><small class="mb-1">수익</small><span id="' + data[i].itmsNm + 'diff' + i +  '"></span>원<small id="' + data[i].itmsNm + 'diffRate' + i +  '">%</small></div></a>'
				}
				useHtml += '</div>'
				document.getElementById('leftSideGround').innerHTML = useHtml;
			}
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX request failed:", textStatus, errorThrown); // 실패 시 로그
        }
    });
}