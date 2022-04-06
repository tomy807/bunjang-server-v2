package com.example.bunjangv2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UserNameOrPasswordNotFoundException(400, "아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    ForbiddenException(401, "해당 요청에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UnAuthorizedException(402, "로그인 후 이용가능합니다.", HttpStatus.UNAUTHORIZED),
    ExpiredJwtException(403, "토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    ReLogin(404, "모든 토큰이 만료되었습니다. 다시로그인하세요.", HttpStatus.UNAUTHORIZED),
    SignatureException(405, "변조된 토큰입니다.", HttpStatus.UNAUTHORIZED),
    NotFoundToken(406,"토큰이 없습니다." ,HttpStatus.BAD_REQUEST),
    UsernameNotFoundException(407, "해당 유저를 찾을수 없습니다.", HttpStatus.NOT_FOUND),;



    private final int code;

    private final String message;

    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
