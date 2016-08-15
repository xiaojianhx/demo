package com.xiaojianhx.demo.springboot.web.resolver;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
public class ExceptionResolver extends SimpleMappingExceptionResolver {

    protected String buildLogMessage(Exception ex, HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        sb.append(writer.getBuffer());
        sb.append("\n\n");
        return sb.toString();
    }

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        System.out.println("异常进来了:" + ex);
        return null;
    }
}