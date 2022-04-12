# 번개장터 REST API Version2


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

---- 오류 ----
#### LazyInitializationException
- LazyInitializationException: failed to lazily initialize a collection of role:
- @AuthenticationPrincipal User user 으로 user을 서비스에 넘기면 영속성 컨텍스트에 인식이 안됨
- 서비스에서 다시 user을 불러오게 해서 오류 해결
#### @ControllerAdvice
Controller에 적용하기 위해 고안된 어노테이션\
모든 @Controller에 대한, 전역적으로 발생할 수 있는 예외를 잡아서 처리할 수 있다.
#### @RestControllerAdvice
@ControllerAdvice와 @ResponseBody를 합쳐놓은 어노테이션\
@ControllerAdvice는 예외만 잡아서 처리하는 반면, 
@RestControllerAdvice는 @ControllerAdvice 역할 뿐만 아니라, 
@ResponseBody를 통해 JSON 형태로 객체를 전달할 수 있다.
#### @ExceptionHandler
어노테이션을 메서드에 선언하고 특정 예외 클래스를 지정해주면
해당 예외가 발생했을 때 메서드에 정의한 로직으로 처리할 수 있다.  
이 어노테이션은 @Controller나 @RestController에 사용해야 한다. 
@Controller, @RestController가 적용된 Bean내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능을 한다.

전역적으로 에러를 핸들링하는 클래스를 생성하고 @ExceptionHandler 어노테이션과 함께 에러 핸들링 메소드를 추가함으로써 에러 처리를 위임할 수 있다.



https://jeong-pro.tistory.com/195  
https://javachoi.tistory.com/253  
https://mangkyu.tistory.com/205

  
https://mangkyu.tistory.com/174