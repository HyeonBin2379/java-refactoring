# 기능 구현 사항 정리

## 구현 프로그램 개요
* 입력한 월에 관한 비상 근무표 생성 프로그램 구현하기

## 비상 근무일 배정 규칙
* 기본: 순번에 따라 비상 근무일을 배정한다.
* 평일과 휴일(토요일, 일요일, 법정공휴일) 비상 근무 순번을 다르게 운영하고 있다.
* 평일 순번과 휴일 순번의 순서는 다를 수 있다.

* 비상 근무자는 평일 순번, 휴일 순번에 각각 1회 편성되어야 한다.
  * 잘못된 예시: 수아가 두 번 편성된 경우
  >평일 순번: 수아, 루루, 글로, 솔로스타, 수아, 슬링키, 참새, 도리, 준팍, 도밥, 고니
  
* 비상 근무자는 어떤 경우에도 연속 2일은 근무할 수 없다.
  순번상 특정 근무자가 연속 2일 근무하게 되는 상황에는, 다음 근무자와 순서를 바꿔 편성한다.
  예를 들어, 수아가 평일인 목요일에 비상 근무를 서고, 다음 날인 금요일이 휴일이면서 순번상 또다시 수아가 근무해야 할 경우,
  다음 휴일 근무자와 순서를 바꿔서 근무한다.
  * 예시)
    * 평일 순번: 준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리
    * 휴일 순번: 수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니
    * 근무 예시: 준팍(월요일),도밥(화요일),고니(수요일),수아(목요일),루루(금요일/휴일),수아(토요일/휴일),...

* 만약에 법정공휴일인 수요일에 수아가 비상 근무를 서고 다음 날 평일 순번이 수아인 경우, 다음 평일 근무자와 순서를 바꿔서 근무한다.
  * 예시)
    * 평일 순번: 준팍,도밥,수아,루루,글로,솔로스타,우코,슬링키,참새,도리,고니
    * 휴일 순번: 수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니
    * 근무 예시: 준팍(월요일),도밥(화요일),수아(수요일/휴일),루루(목요일),수아(금요일),루루(토요일/휴일),글로(일요일/휴일),...

* 비상 근무자 배정 시 다음 근무자와 순서를 바꿔야 하는 경우에는, 앞의 날짜부터 순서를 변경해야 한다.

## 비상 근무자 배정 시 유의사항
* 비상근무자의 이름은 최대 5글자까지만 가능
* 비상근무자는 평일, 휴일 합쳐서 최소 5명, 최대 35명까지 입력 가능

## 법정 공휴일
* 1월 1일
* 3월 1일
* 5월 5일
* 6월 6일
* 8월 15일
* 10월 3일
* 10월 9일
* 12월 25일

## 입력 요구사항
* 입력해야 할 사항은 아래의 3가지
* `비상 근무를 배정할 월과 시작 요일을 입력하세요>` 메시지 출력
  * 월(숫자)와 시작 요일(일,월,화,수,목,금,토) 정보 입력
    * 시작 요일 입력 시 입력한 월의 1일에 해당하는 요일을 설정
      * 시작 요일을 기준으로 그 달의 평일과 휴일을 구분한다.
      * 휴일: 주말(토요일, 일요일)과 법정공휴일
    * 윤년, 평년에 관계없이 2월은 항상 28일이라고 가정
    * 월, 시작 요일 입력값이 올바르지 않으면 다시 입력
    * 월은 정수, 시작 요일은 지정된 문자여야 함
    
* `평일 비상 근무 순번대로 사원 닉네임을 입력하세요>` 메시지 출력
  * 평일 비상 근무 순서 입력
  * 잘못된 쉼표 입력, 비상근무자 이름 길이 초과, 입력 가능한 비상근무자 범위 미충족 시 재입력
  * 동일한 이름이 2회 이상 입력된 경우에도 재입력
* `휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>` 메시지 출력
  * 휴일(토요일, 일요일, 법정공휴일) 비상근무 순서 입력
    * 휴일 비상 근무 순서 입력
    * 잘못된 쉼표 입력, 비상근무자 이름 길이 초과, 입력 가능한 비상근무자 범위 미충족 시 재입력
    * 동일한 이름이 2회 이상 입력된 경우에도 재입력
* 올바르지 않은 입력 시 IllegalArgumentException 발생 & [ERROR] 메시지 출력 후 재입력

## 출력 시 요구사항
* 평일이면서 법정공휴일인 경우에만 요일 뒤에 (휴일) 표기 필수
  * 즉, 범정공휴일이 주말이면 요일 뒤에 (휴일) 미표기
* 비상근무표 출력 완료 시 프로그램 종료