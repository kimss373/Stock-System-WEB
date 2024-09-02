/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
	//매수, 매도 클릭 시 오른쪽 사이드바
    const toggleButton = document.getElementById('bttn');
    const toggleButton1 = document.getElementById('bttn1');
    const targetElement = document.getElementById('buy-sidebar');
    toggleButton.addEventListener('click', () => {
        if (targetElement.classList.contains('active') && sellButton.classList.contains('active')){
			buyButton.classList.add('active');
	        sellButton.classList.remove('active');
	        submitButton.innerHTML = '매수';
	        submitButton.classList.remove('sell');
	        submitButton.classList.add('buy');
		} else if (!targetElement.classList.contains('active')){
			targetElement.classList.toggle('active');
	        buyButton.classList.add('active');
	        sellButton.classList.remove('active');
	        submitButton.innerHTML = '매수';
	        submitButton.classList.remove('sell');
	        submitButton.classList.add('buy');
        } else if (targetElement.classList.contains('active') && buyButton.classList.contains('active')){
	        targetElement.classList.toggle('active');
		}
		document.getElementById('buyOrSell').value = 'buy';
		selectAccount();
    });
    toggleButton1.addEventListener('click', () => {
		if (targetElement.classList.contains('active') && buyButton.classList.contains('active')){
	        sellButton.classList.add('active');
	        buyButton.classList.remove('active');
	        submitButton.innerHTML = '매도';
	        submitButton.classList.remove('buy');
	        submitButton.classList.add('sell');
        } else if (!targetElement.classList.contains('active')){
	        targetElement.classList.toggle('active');
			sellButton.classList.add('active');
	        buyButton.classList.remove('active');
	        submitButton.innerHTML = '매도';
	        submitButton.classList.remove('buy');
	        submitButton.classList.add('sell');
		} else if (targetElement.classList.contains('active') && sellButton.classList.contains('active')){
	        targetElement.classList.toggle('active');
		}
		document.getElementById('buyOrSell').value = 'sell';
		selectAccount();
    });
	
    const buyButton = document.getElementById('buyButton');
    const sellButton = document.getElementById('sellButton');
    const submitButton = document.querySelector('.submit-button');

    if (buyButton && sellButton && submitButton) {
        buyButton.addEventListener('click', function() {
            buyButton.classList.add('active');
            sellButton.classList.remove('active');
            submitButton.innerHTML = '매수';
            submitButton.classList.remove('sell');
            submitButton.classList.add('buy');
            document.getElementById('buyOrSell').value = 'buy';
            selectAccount();
        });

        sellButton.addEventListener('click', function() {
            sellButton.classList.add('active');
            buyButton.classList.remove('active');
            submitButton.innerHTML = '매도';
            submitButton.classList.remove('buy');
            submitButton.classList.add('sell');
            document.getElementById('buyOrSell').value = 'sell';
            selectAccount();
        });
    } else {
        console.error('One or both buttons were not found.');
    }
    
	var priceVal = document.getElementById('price');
	priceVal.addEventListener("input", function(){
		
		var value = this.value;
		var nowBalance = parseInt(document.getElementById('nowBalance').innerText.replace(/[^0-9]/g, ''), 10);
		
		if (document.getElementById('buyOrSell').value == 'buy'){
			document.getElementById('totalValue').innerHTML = (document.getElementById('amount').value * value).toLocaleString();
			var maxAmt = Math.floor(nowBalance/value);
			document.getElementById('showMaxAmount').innerHTML = maxAmt;
			document.getElementById('amount').setAttribute('max', maxAmt);
		} 
	})
	
	var amountVal = document.getElementById('amount');
	amountVal.addEventListener("input", function(){
		
		var value = this.value;
		document.getElementById('totalValue').innerHTML = (priceVal.value * value).toLocaleString();
	})
	   
   
});

function selectAccount(){
	
	let opt = document.getElementById('selectAccountRightSide');
	let optVal = opt.options[opt.selectedIndex].value;
	if (optVal == '0') {
		document.getElementById('nowBalance').innerHTML = '0';
		return;
	}
	
	$.ajax({
        url: './ajax/getOneAccountInfo.jsp',
        method: 'GET',
        data: { accountNum: optVal },
        dataType: 'json', // JSON 형식으로 응답을 받음
        success: function(data) {
			let nowStockHolding = data.holdings;
			let accountVO = data.account;
			let tradingList = data.tradings;
		    var priceVal = document.getElementById('price').value;
			document.getElementById('formAccountNum').value = accountVO.accountNum;
			document.getElementById('amount').value=0;
			document.getElementById('totalValue').innerHTML = 0;
			// if buy
            document.getElementById('nowBalance').innerHTML = accountVO.balance.toLocaleString();
			if (document.getElementById('buyOrSell').value == 'buy'){
				document.getElementById('showMaxAmount').innerHTML = Math.floor(accountVO.balance/priceVal);
				document.getElementById('amount').setAttribute('max', Math.floor(accountVO.balance/priceVal));
			} else if (document.getElementById('buyOrSell').value == 'sell'){
			// if sell map에서 꺼내서 ㄲ
				var tmpAmount = 0;
				var tmpName = lastDayStock.itmsNm;
				if (nowStockHolding.hasOwnProperty(lastDayStock.itmsNm)) tmpAmount = nowStockHolding[tmpName].stockCount;
				document.getElementById('showMaxAmount').innerHTML = tmpAmount;
				document.getElementById('amount').setAttribute('max', tmpAmount);
			}
			
			if (tradingList.length == 0) {
				document.getElementById('order-history').innerHTML ='<div class="history-label">주문내역</div><div class="history-content">주문 내역이 없습니다.</div>'
			} else {
				let useHtml = '<div class="history-label">주문내역</div><ul class="list-group">'
				for (let i = 0; i < tradingList.length; i++) {
					if (tradingList[i].tradingType == 'B'){
						useHtml += '<li class="list-group-item d-flex justify-content-between">' + tradingList[i].srtnCd + '<br>매수 ' + tradingList[i].stockPrice + '원<span>' + tradingList[i].stockCount + '주</span><button type="button" class="btn-close" aria-label="Close"></button></li>'
					} else {
						useHtml += '<li class="list-group-item d-flex justify-content-between">' + tradingList[i].srtnCd + '<br>매도 ' + tradingList[i].stockPrice + '원<span>' + tradingList[i].stockCount + '주</span><button type="button" class="btn-close" aria-label="Close"></button></li>'
					}
				}
				useHtml += '</ul>'
				document.getElementById('order-history').innerHTML = useHtml;
			}
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX request failed:", textStatus, errorThrown); // 실패 시 로그
        }
    });
    
		
}

function buyWithAutoCalc(divide){
	let buyButton = document.getElementById('buyButton');
	let sellButton = document.getElementById('sellButton');
	
	let priceVal = document.getElementById('price').value;
	let nowBalanceVal = parseInt(document.getElementById('nowBalance').innerText.replace(/[^0-9]/g, ''), 10);
	
	if (buyButton.classList.contains('active')){
		var amountVal = Math.floor(Math.floor(nowBalanceVal/priceVal)/divide);
		document.getElementById('amount').value = amountVal;
		document.getElementById('totalValue').innerHTML = (priceVal * amountVal).toLocaleString();
	} else if (sellButton.classList.contains('active')){
		var amountVal = Math.floor(Number(document.getElementById('showMaxAmount').innerText)/divide);
		document.getElementById('amount').value = amountVal;
		document.getElementById('totalValue').innerHTML = (priceVal * amountVal).toLocaleString();
	}
	
}

function buyOrSellStock(){
	
	var amountVal = document.getElementById('amount').value;
	if (amountVal == 0) {
		alert('수량을 입력하세요');
		return false;
	} else {
		return true;
	}
	
}

function buyOrSellActionSubmit() {
	var formData = $('#buyOrSellAction').serialize();
	
	$.ajax({
        url: '/Stock-System-WEB/ajax/buyOrSellStock.jsp',
        type: 'POST',
        data: formData,
        success: function(response) {
			response = JSON.parse(response);
            alert(response.msg);
            selectAccount();
            // 서버로부터 받은 응답에 따른 추가 작업을 수행할 수 있습니다.
        },
        error: function(xhr, status, error) {
            console.error('Error submitting form data:', error);
        }
    });
}