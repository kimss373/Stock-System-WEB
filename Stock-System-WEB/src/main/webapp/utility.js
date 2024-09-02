/**
 * 
 */
//2초마다 값을 바꿔주는 함수 시작
const openPrice = lastDayStock.mkp; // 시가
const closePrice = lastDayStock.clpr; // 종가
let fltRt = lastDayStock.fltRt;
let nowPrice = lastDayStock.mkp;
let lastDayStockItmsNm = lastDayStock.itmsNm;


function getHogaUnit(price) {
    if (price < 1000) return 1;
    if (price < 5000) return 5;
    if (price < 10000) return 10;
    if (price < 50000) return 50;
    if (price < 100000) return 100;
    if (price < 500000) return 500;
    return 1000;
}

function getRandomNumberBetween(min, max) {
    // min과 max 사이의 임의의 숫자를 단위에 맞게 생성하는 함수
    var hogaUnit = getHogaUnit(nowPrice);
    var direction = Math.random() < 0.5 ? -1 : 1; // -1 또는 1 중 랜덤 선택
    var tmp = nowPrice + (hogaUnit * direction);
    // 시가와 종가 사이로 제한
    if (tmp < min) tmp = min;
    if (tmp > max) tmp = max;
    //fltRt = ((tmp - last2DayClpr) * 100 / last2DayClpr).toFixed(2); 
    
    //오른쪽 사이드 바 가격 설정 step
    var costStep = document.getElementById('price');
    costStep.setAttribute('step', hogaUnit)
    return tmp;
}

function formatNumber(number) {
    // 숫자를 3자리마다 쉼표로 구분하는 함수
    return number.toLocaleString();
}

function updateNumber() {
	console.log(sessionUsername);
	$.ajax({
        url: './ajax/updateStocksPrice.jsp',
        method: 'GET',
        dataType: 'json', // JSON 형식으로 응답을 받음
        success: function(ajaxResponse) {
			let dataList = ajaxResponse.stockMap;
		    if (ajaxResponse.toggleSign){
				console.log('거래완료실행됨');
				selectAccount();
				selectAccountForHoldingStock();
			}
			let evalProceed = 0;
			// 현재 보고있는 주식의 데이터 변동 코드 시작
			nowPrice = dataList[lastDayStockItmsNm].clpr;
			// 가격 step 설정
			var hogaUnit = getHogaUnit(nowPrice);
			var costStep = document.getElementById('price');
    		costStep.setAttribute('step', hogaUnit)
    		//
			fltRt = ((nowPrice - last2DayClpr) * 100 / last2DayClpr).toFixed(2); 
    		const formattedNumber = formatNumber(nowPrice);
    		document.getElementById('chartHead1').innerHTML = lastDayStock.itmsNm + ' (' + lastDayStock.mrktCtg + ') ' + formattedNumber;
    		document.getElementById('chartHead2').innerHTML = fltRt + '%';
    		document.getElementById('chartHead3').innerHTML = '거래량 ' + formatNumber(lastDayStock.trqu) + ' / 거래대금 ' + formatNumber(lastDayStock.trprc) + '원';
    		document.getElementById('buyPrice').innerHTML = formattedNumber;
		    document.getElementById('buyName').innerHTML = lastDayStock.itmsNm;
		    document.getElementById('buyInfo').innerHTML = lastDayStock.srtnCd + ' ' + lastDayStock.mrktCtg;
		    var nowTotalAssets = nowBalance;
		    if (sessionUsername != null) {
			    for (let i = 0; i < nowLeftSideAccount.length ; i++) {
					evalProceed += ((dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount) - nowLeftSideAccount[i].stockPrice);
					//nowLeftSideAccount 필요 없고 dataList로 하면 될듯
					nowTotalAssets += dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount;
				    document.getElementById(nowLeftSideAccount[i].itmsNm + 'price' + i).innerHTML = dataList[nowLeftSideAccount[i].itmsNm].clpr;
				    document.getElementById(nowLeftSideAccount[i].itmsNm + 'totalPrice' + i).innerHTML = (dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount).toLocaleString();
				    document.getElementById(nowLeftSideAccount[i].itmsNm + 'diff' + i).innerHTML = ((dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount) - nowLeftSideAccount[i].stockPrice).toLocaleString();
				    document.getElementById(nowLeftSideAccount[i].itmsNm + 'diffRate' + i).innerHTML = ((((dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount) - nowLeftSideAccount[i].stockPrice)/(nowLeftSideAccount[i].stockPrice))*100).toFixed(2) + '%';
				    
					document.getElementById(nowLeftSideAccount[i].itmsNm + 'diff' + i).classList.remove("upRising", "downFall");
					document.getElementById(nowLeftSideAccount[i].itmsNm + 'diffRate' + i).classList.remove("upRising", "downFall");
				    //수익 빨강 파랑 부여
				    if (((dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount) - nowLeftSideAccount[i].stockPrice) > 0) {
						document.getElementById(nowLeftSideAccount[i].itmsNm + 'diff' + i).classList.add("upRising");
					} else if (((dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount) - nowLeftSideAccount[i].stockPrice) < 0) {
						document.getElementById(nowLeftSideAccount[i].itmsNm + 'diff' + i).classList.add("downFall");
					}
					if (((((dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount) - nowLeftSideAccount[i].stockPrice)/(nowLeftSideAccount[i].stockPrice))*100).toFixed(2) > 0) {
						document.getElementById(nowLeftSideAccount[i].itmsNm + 'diffRate' + i).classList.add("upRising");
					} else if (((((dataList[nowLeftSideAccount[i].itmsNm].clpr * nowLeftSideAccount[i].stockCount) - nowLeftSideAccount[i].stockPrice)/(nowLeftSideAccount[i].stockPrice))*100).toFixed(2) < 0) {
						document.getElementById(nowLeftSideAccount[i].itmsNm + 'diffRate' + i).classList.add("downFall");
					}
				    //수익 퍼센트 빨강 파랑 부여
				}
				if (nowLeftSideAccount.length != 0) {
					document.getElementById('evaluationProceeds').innerHTML = evalProceed;
					document.getElementById('evaluationProceeds').classList.remove("upRising", "downFall");
					if (evalProceed > 0) {
							document.getElementById('evaluationProceeds').classList.add("upRising");
					} else if (evalProceed < 0) {
							document.getElementById('evaluationProceeds').classList.add("downFall");
					}
				}
			}
			document.getElementById('totalAssets').innerHTML = '총 자산 : ' + nowTotalAssets.toLocaleString() + '원';
			
		    // 현재 보고있는 주식의 데이터 변동 코드 끝
		    
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX request failed:", textStatus, errorThrown); // 실패 시 로그
        }
    }); 
    //nowPrice = getRandomNumberBetween(Math.min(openPrice, closePrice), Math.max(openPrice, closePrice));
    //const formattedNumber = formatNumber(nowPrice);
    //document.getElementById('chartHead1').innerHTML = lastDayStock.itmsNm + ' (' + lastDayStock.mrktCtg + ') ' + formattedNumber;
    //document.getElementById('chartHead2').innerHTML = fltRt + '%';
    //document.getElementById('chartHead3').innerHTML = '거래량 ' + formatNumber(lastDayStock.trqu) + ' / 거래대금 ' + formatNumber(lastDayStock.trprc) + '원';
    //document.getElementById('buyPrice').innerHTML = formattedNumber;
    //document.getElementById('buyName').innerHTML = lastDayStock.itmsNm;
    //document.getElementById('buyInfo').innerHTML = lastDayStock.srtnCd + ' ' + lastDayStock.mrktCtg;
    
}

// 2초마다 updateNumber 함수 실행
setInterval(updateNumber, 3000);

// 초기 숫자 업데이트
updateNumber();
/////////////////////////////////2초마다 값을 바꿔주는 함수 끝

let mesu = 0;
let medo = 0;

function fetchData(query) {
    return $.ajax({
        url: './ajax/getAllStockName.jsp',
        method: 'GET',
        data: { query: query },
        dataType: 'json', // JSON 형식으로 응답을 받음
        success: function(dataList) {
            processDropdown(dataList, query);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX request failed:", textStatus, errorThrown); // 실패 시 로그
        }
    });
}

function processDropdown(dataList, query) {
    var dropdown = document.getElementById('dropdown');
    var searchInput = document.getElementById('searchInput');
    
    var filteredData = dataList.filter(item => item.includes(query));
    dropdown.innerHTML = "";
    if (filteredData.length > 0) {
        filteredData.forEach(function(item) {
            var div = document.createElement("div");
            div.innerHTML = item.replace(new RegExp(query, "gi"), match => `<strong>${match}</strong>`);
            div.addEventListener("click", function() {
                searchInput.value = item;
                dropdown.innerHTML = "";
                dropdown.style.display = "none";
            });
            dropdown.appendChild(div);
        });
        dropdown.style.display = "block";
    } else {
        dropdown.style.display = "none";
    }
}

document.addEventListener('DOMContentLoaded', (event) => {
    
    //오른쪽 사이드바에서 탭
    const navLinks = document.querySelectorAll('.nav-link:not(.disabled)');
    const subNavLinks = document.querySelectorAll('.sub-nav-link');

    function handleNavLinkClick(e) {
        e.preventDefault();

        // 현재 활성화된 상위 탭과 그 콘텐츠 비활성화
        const activeNavLink = document.querySelector('.nav-link.active');
        const activeTabContent = document.querySelector('.tab-content.active');
        if (activeNavLink) {
            activeNavLink.classList.remove('active');
        }
        if (activeTabContent) {
            activeTabContent.classList.remove('active');
        }

        // 클릭된 상위 탭과 그 콘텐츠 활성화
        this.classList.add('active');
        const targetContent = document.querySelector(this.dataset.target);
        if (targetContent) {
            targetContent.classList.add('active');
        }

        // 하위 탭 초기화
        const subNav = document.querySelector(this.dataset.target + ' .sub-nav-link.active');
        const subContent = document.querySelector(this.dataset.target + ' .sub-tab-content.active');
        if (subNav) {
            subNav.classList.remove('active');
        }
        if (subContent) {
            subContent.classList.remove('active');
        }

        // 첫 번째 하위 탭 활성화 (있을 경우)
        const firstSubNav = document.querySelector(this.dataset.target + ' .sub-nav-link');
        if (firstSubNav) {
            firstSubNav.classList.add('active');
            const firstSubContent = document.querySelector(firstSubNav.dataset.target);
            if (firstSubContent) {
                firstSubContent.classList.add('active');
            }
        }
    }

    function handleSubNavLinkClick(e) {
        e.preventDefault();

        // 현재 활성화된 하위 탭과 그 콘텐츠 비활성화
        const activeSubNav = this.closest('.tab-content').querySelector('.sub-nav-link.active');
        const activeSubContent = this.closest('.tab-content').querySelector('.sub-tab-content.active');

        if (activeSubNav) {
            activeSubNav.classList.remove('active');
        }
        if (activeSubContent) {
            activeSubContent.classList.remove('active');
        }

        // 클릭된 하위 탭과 그 콘텐츠 활성화
        this.classList.add('active');
        const targetContent = document.querySelector(this.dataset.target);
        if (targetContent) {
            targetContent.classList.add('active');
        }
    }

    // 상위 탭 클릭 이벤트 리스너 추가
    navLinks.forEach(link => {
        link.addEventListener('click', handleNavLinkClick);
    });

    // 하위 탭 클릭 이벤트 리스너 추가
    subNavLinks.forEach(subLink => {
        subLink.addEventListener('click', handleSubNavLinkClick);
    });
    
    
    // ajax 검색
	var searchInput = document.getElementById('searchInput');
    var currentFocus = -1;
    
    searchInput.addEventListener("input", function() {
        var value = this.value;
        if (!value) {
            dropdown.innerHTML = "";
            dropdown.style.display = "none";
            return;
        }

        fetchData(value);
    });

    searchInput.addEventListener("keydown", function(e) {
        var items = dropdown.getElementsByTagName("div");
        if (e.keyCode === 40) {
            currentFocus++;
            addActive(items);
        } else if (e.keyCode === 38) {
            currentFocus--;
            addActive(items);
        } else if (e.keyCode === 13) {
            e.preventDefault();
            if (currentFocus > -1) {
                if (items) items[currentFocus].click();
            }
        }
    });

    function addActive(items) {
        if (!items) return false;
        removeActive(items);
        if (currentFocus >= items.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (items.length - 1);
        items[currentFocus].classList.add("highlight");
        
        // 스크롤 조정
        items[currentFocus].scrollIntoView({
            behavior: 'smooth',
            block: 'nearest',
            inline: 'start'
        });
    }

    function removeActive(items) {
        for (var i = 0; i < items.length; i++) {
            items[i].classList.remove("highlight");
        }
    }

    document.addEventListener("click", function(e) {
        if (!e.target.matches('#searchInput')) {
            dropdown.innerHTML = "";
            dropdown.style.display = "none";
        }
    });
});
