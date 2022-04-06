package com.example.bunjangv2.config.security;

import com.example.bunjangv2.exception.ErrorCode;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");
        ErrorCode errorCode;

        if (exception == null) {
            errorCode = ErrorCode.UnAuthorizedException;
            setResponse(response, errorCode);

        }
        else if (exception.equals("ExpiredJwtException")) {
            errorCode = ErrorCode.ExpiredJwtException;
            setResponse(response, errorCode);
        } else if (exception.equals("SignatureException")) {
            errorCode = ErrorCode.SignatureException;
            setResponse(response, errorCode);
        } else if (exception.equals("NotFoundToken")) {
            errorCode = ErrorCode.NotFoundToken;
            setResponse(response, errorCode);
        } else if (exception.equals("UsernameNotFoundException")) {
            errorCode = ErrorCode.UsernameNotFoundException;
            setResponse(response, errorCode);
        }


    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", errorCode.getMessage());
        responseJson.put("code", errorCode.getCode());

        response.getWriter().print(responseJson);
    }
}
