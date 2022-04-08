package com.example.bunjangv2.config.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secretKey = "diwhdiwq";

    // 토큰 유효시간 300분
    private long tokenValidTime = 30 * 60 * 1000000L;

    private final UserDetailsService userDetailsService;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(Long userIdx,String email) {
        Date now = new Date();
        return Jwts.builder()
                .claim("userIDx", userIdx)
                .claim("email", email)// 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email", String.class);
    }



    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("X-AUTH-TOKEN");
        if (token == null || token.isEmpty()) {
            request.setAttribute("exception","NotFoundToken");
        }
        return token;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(ServletRequest request, String jwtToken) {

        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        } catch (MalformedJwtException e) {
            request.setAttribute("exception","MalformedJwtException");
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception","ExpiredJwtException");
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception","UnsupportedJwtException");
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception","IllegalArgumentException");
        } catch (SignatureException e) {
            request.setAttribute("exception", "SignatureException");
        }
        return false;
    }
}
