package com.xiaojianhx.demo.springboot.web.interceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor extends MainInterceptor {

    private Logger logger = Logger.getLogger(LogInterceptor.class);

    private ThreadLocal<Long> time = new ThreadLocal<Long>();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        System.out.println("[Log Interceptor is executing]");
        logger.debug("[Log Interceptor is executing]");

        time.set(System.currentTimeMillis());

        logger.debug(clientInfo(request));
        logger.debug("[" + getHandlerObj(obj) + "]");

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView m) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {

        long endTime = System.currentTimeMillis();

        logger.debug("[" + getHandlerObj(obj) + "]");
        logger.info(clientInfo(request) + "[" + (endTime - time.get()) + "ms]");
        logger.debug("[Log Interceptor is executed]");
    }

    private String getHandlerObj(Object obj) {

        String handlerObj = String.valueOf(obj);

        if (obj instanceof HandlerMethod) {

            HandlerMethod hm = (HandlerMethod) obj;
            handlerObj = hm.getBeanType().getName() + "." + hm.getMethod().getName();
        }

        return handlerObj;
    }

    private StringBuffer clientInfo(HttpServletRequest request) {

        StringBuffer buf = new StringBuffer();
        // buf.append("[" + ClientUtils.getRemoteAddress(request) + "]");
        // buf.append("[" + SessionManager.getInstance().getUsername() + "(" +
        // SessionManager.getInstance().getUid() + ")" + "]");
        buf.append("[" + servletPath(request) + "]");

        return buf;
    }

    private String contextPath(HttpServletRequest request) {

        StringBuffer buf = new StringBuffer();

        buf.append(request.getScheme() + "://");
        // buf.append(request.getServerName() + ":");
        try {
            buf.append(InetAddress.getLocalHost().getHostAddress() + ":");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        buf.append(request.getServerPort());
        buf.append(request.getContextPath());

        return buf.toString();
    }

    private String servletPath(HttpServletRequest request) {
        return contextPath(request) + request.getServletPath();
    }
}