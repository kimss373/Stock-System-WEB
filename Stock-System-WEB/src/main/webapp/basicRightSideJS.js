/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    // 기본 탭과 서브 탭 열기
    document.getElementById('defaultOpen').click();
    document.getElementById('defaultSubOpen').click();
});

function openTab(evt, tabName) {
    var i, tabcontent, tablinks;

    // 모든 tabcontent 숨기기
    tabcontent = document.getElementsByClassName('tabcontent');
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = 'none';
    }

    // 모든 tablinks에서 active 클래스 제거
    tablinks = document.getElementsByClassName('tablinks');
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(' active', '');
    }

    // 선택한 tabcontent 보이기
    document.getElementById(tabName).style.display = 'block';
    evt.currentTarget.className += ' active';
}

function openSubTab(evt, subTabName) {
    var i, subtabcontent, subtablinks;

    // 모든 subtabcontent 숨기기
    subtabcontent = document.getElementsByClassName('subtabcontent');
    for (i = 0; i < subtabcontent.length; i++) {
        subtabcontent[i].style.display = 'none';
    }

    // 모든 subtablinks에서 active 클래스 제거
    subtablinks = document.getElementsByClassName('subtablinks');
    for (i = 0; i < subtablinks.length; i++) {
        subtablinks[i].className = subtablinks[i].className.replace(' active', '');
    }

    // 선택한 subtabcontent 보이기
    document.getElementById(subTabName).style.display = 'block';
    evt.currentTarget.className += ' active';
}
