package com.msunsoft.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  性能计数拦截器.
 */
public class PerfInfoHandlerInterceptor extends HandlerInterceptorAdapter {
    private  static Logger logger = LoggerFactory.getLogger(PerfInfoHandlerInterceptor.class);
    //为了多线程使用定义本地线程
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("startTime");
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime); //线程绑定变量，（数据只对当前线程可见）
        return true; //继续流程
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();// 得到线程绑定数据
        long consumeTime = endTime-beginTime;
        logger.info("url:{},耗时{}毫秒",request.getRequestURI(),consumeTime);
    }
}
