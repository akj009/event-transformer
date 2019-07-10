package com.mptyminds.eventtransformer;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
@Log4j2
public class ValidSourceFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        final String requestURI = req.getRequestURI();
        log.info(
                "Logging Request  {} : {}", req.getMethod(),
                requestURI);
        filterChain.doFilter(servletRequest, servletResponse);
        log.info(
                "Logging Response :{}",
                res.getContentType());
    }
}
