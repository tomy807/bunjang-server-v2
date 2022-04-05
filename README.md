# 번개장터 REST API Version2


## 2022-04-05 
1. Spring Security Authentication 추가
2. jdbcTemplate->JPA 변경
3. JwtToken 설정 변경
4. WebSecurityConfig 설정 변경


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