# Stock-System-WEB
# 모의 주식 투자 서비스 개발 
## 프로젝트 소개
JSP(JavaServer Pages)와 서블릿(Servlet)을 활용하여 MVC(Model-View-Controller) 패턴으로 개발한 모의 주식 투자 웹 사이트입니다. 이 웹 사이트는 사용자가 가상 자금을 사용해 주식 거래를 연습하고, 실시간으로 주식 시세를 확인하며, 투자 전략을 테스트할 수 있는 환경을 제공하는 웹 사이트를 구현하였습니다.

## 개발 환경 및 사용 기술
### 개발 환경
1. Java17, JavaScript, Oracle-SQL, HTML5/CSS3
2. Oracle
3. Window10, Github

### 사용 기술
1. MyBatis - SQL 쿼리를 Java 분리하여 간결한 코드로 작성하기 위해 사용
2. JDBC - 오라클과 Connection 생성을 위해 사용
3. JSTL, EL, JSP - 동적 웹사이트를 Java 친화적으로 구현하기 위해 사용
4. Ajax - 비동기 통신을 통해 빠른 반응형 페이지를 만들기 위해 사용
5. DB링크 - 증권, 은행, 카드 같은 다른 웹 사이트와 확장성을 확보하고 DB를 공유하기 위해 사용
6. Redis - 하나의 사이트에서 로그인을 하더라도 다른 곳에서 까지 로그인이 유지되는 것을 구현하기 위해 사용

## 세부 기능
1. 회원 별 모의 계좌 생성 및 관리
2. 주식 api를 이용해 이전날까지의 데이터를 database에 저장
3. 실시간 변동되는 데이터를 사용하는데의 한계, 이전날까지의 데이터를 이용해 2초마다 상한, 하한가 사이로 주가가 변동되도록 구현
4. 주식 검색, 드랍 다운 자동완성
5. 주가 변동 그래프 제공
6. 가격 변동이 큰 주식의 정보 제공
7. 주식 매도, 매수

## ERD


