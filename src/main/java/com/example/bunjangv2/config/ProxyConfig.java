package com.example.bunjangv2.config;

import com.example.bunjangv2.trace.LogTrace;
import com.example.bunjangv2.trace.LogTraceAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {

    @Bean
    public Advisor advisor(LogTrace logTrace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.example.bunjangv2..*(..))");
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
