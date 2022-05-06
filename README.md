# 번개장터 REST API Version2
라이징캠프에서 했던 번개장터 클론 코딩을 조금 더 다양한 기술을 사용해보고 싶어 새롭게 프로젝트를 진행해보았다.
ERD는 핵심적인 테이블만 남겨두고 나머지는 제외하였다.
- jdbcTemplate을 jpa로 바꿀것
- RestControllerAdvice를 사용하여 지저분한 try-catch문을 대거 삭제할것
- AOP를 적용하여 트랜잭션 흐름을 화살표로 나타낼것
- JWT 관련 예외처리들을 Filter에서 처리할수 있도록 할 예정
- authentication를 이용하여 로그인한 사용자 정보 가져올것
- Redies,jenkins,kafka 사용해볼것
- swagger 적용

## 개발중 느낀점
1. jpa는 굉장히 편하고 SQL를 작성할 필요가 없어서 좋지만 잘 모르고 쓰면 나도 모르게 어마어마한 오류나 실수가 발생할거 같은 느낌이 든다.
2. 여러 테이블을 조인 하고 싶은데 유독 Order테이블에서만 오류가 뜨고 있다. 예약어이기 때문에 오류가 난거 같은데 테이블이나 엔티티의 이름을 바꿔 보아도 해결이 안된다.
3. 오늘 기술과제에서 탈락했는데 과제에 대한 피드백을 주셨다.
- service 객체는 host로써 비즈니스 로직의 흐름을 관리해주고 세부 로직은 함수나, 다른 객체들이 담당 해 주었으면 하는 부분이 아쉬웠습니다.
- 계산 로직을 담당하는 함수는 좋았습니다만 데이터조회, valid 부분도 분리가 되었다면 더 좋았을 것 같습니다.
- 계산하는 함수에서 쿠폰과 프로모션 또한 if 분기가 아닌 개별로 처리되기를 기대했습니다. 쿠폰/프로모션의 계산 방식이 바뀌면 getDicountAmoun() 함수 전체에 영향을 받기 때문입니다. (전략패턴을 추천합니다.)
- 테스트코드의 누락이 아쉬웠습니다. 테스트 코드를 통해 소스코드의 신뢰성을 확보하면 좋겠다고 생각했습니다. 
4. 자바 기본에 대해 다시 공부해봐야할것 같다. 클래스,메소드를 어떤 식으로 나눌지 고민해보자.
5. SOLID원칙,디자인 패턴을 다시 학습과 적용 필요.
6. 테스트코드는 무조건 작성하자.
### 개발일지
## 2022-04-05 
1. Spring Security Authentication 추가
2. jdbcTemplate->JPA 변경
3. JwtToken 설정 변경
4. WebSecurityConfig 설정 변경
5. MySql->H2(테스트용)
## 2022-04-06
1. Spring Security 를 이용하여 Jwt을 설정하면 필터에서 적용되기 때문에 Controller까지 오지않기 때문에
   RestControllerAdvice에서 예외처리를 할수 없다.
2. 필터에서 예외처리하기 위해 AuthenticationEntryPoint,AccessDeniedHandler를 
필터에 추가하여 exception이 발생하면 필터에서 잡아
주도록 추가하였다.
3. 상품을 등록하기 위한 Api 만드는중(DTO와 Entity 매핑 관련하여 방법을 찾는중)
## 2022-04-08
1. 상품 생성 API 
- (1) 상품에 직거래 장소를 입력하지 않았을 경우 "지역정보없음"으로 자동 저장 
- (2) 만약 직거래 장소를 선택하고 싶다면 Address에 등록한 MAIN direct Address를 선택한다
2. Address 생성 API 
- (1) direct 장소 등록-> 직거래 장소를 등록한다.
- (2) delivery 주소 등록 -> 배달 받는 주소를 등록한다.
- (3) 공통 
- TYPE에는 MAIN과 SUB가 있어서 MAIN을 자동으로 가져온다. 
- 주소는 총 3개까지 동록할수 있다.
- MAIN이 이미 있는데 MAIN을 또 등록하면 이미 등록된 MAIN은 SUB로 바뀐다.
## 2022-04-09
1. 주문 생성 API
- (1) 직거래 주문 방법-> 상품에 저장된 직거래 장소를 가져온다.
- (2) 배달 주문 방법-> 자신의 Address에 저장된 MAIN을 주문에 저장한다.
- 공통
- 상품의 개수는 한개 줄어들고 만약 상품의 개수가 0이면 상품 상태는 RESERVED로 바뀐다.
- 구매자의 point는 상품 가격 만큼 줄어들고 판매자는 상품 가격 만큼 증가한다.
- 만약 구매자의 point 가 상품 가격 보다 적으면 예외를 던져준다.
## 2022-04-10~~
인적성과 코딩테스트 기술 과제 때문에 프로젝트를 못하고 있다.시간이 조금 더 나면 다시 시작해 보자 
### 오류
#### LazyInitializationException
- LazyInitializationException: failed to lazily initialize a collection of role:
- @AuthenticationPrincipal User user 으로 user을 서비스에 넘기면 영속성 컨텍스트에 인식이 안됨
- 서비스에서 다시 user을 불러오게 해서 오류 해결->X
- Swagger를 적용했을때 @AuthenticationPrincipal를 파라미터로 같이 가져와서 오류->@AuthenticationPrincipal를 아예 삭제하고 SecurityContextHolder으로 사용자 정보 불러오는 것으로 바꿈
