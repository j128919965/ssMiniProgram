package xyz.lizhaorong.filter;

import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 9)
public class LogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String remoteAddr = ServletUtil.getClientIP(request);

        log.trace("");
        log.trace("Starting url: [{}], method: [{}], ip: [{}]", request.getRequestURL(), request.getMethod(), remoteAddr);

        // Set start time
        long startTime = System.currentTimeMillis();

        // Do filter
        filterChain.doFilter(request, response);

        log.trace("Ending   url: [{}], method: [{}], ip: [{}], status: [{}], usage: [{}] ms", request.getRequestURL(), request.getMethod(), remoteAddr, response.getStatus(), System.currentTimeMillis() - startTime);
        log.trace("");
    }
}
