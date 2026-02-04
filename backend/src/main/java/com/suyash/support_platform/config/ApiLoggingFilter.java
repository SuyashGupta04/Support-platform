package com.suyash.support_platform.config;

import com.suyash.support_platform.model.ApiLog;
import com.suyash.support_platform.repository.ApiLogRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ApiLoggingFilter extends OncePerRequestFilter {
        @Autowired
        private ApiLogRepository repo;

        @Override
        protected void doFilterInternal(
                HttpServletRequest req,
                HttpServletResponse res,
                FilterChain chain
        ) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        chain.doFilter(req,res);
        long duration = System.currentTimeMillis()-start;

        ApiLog log = new ApiLog();
        log.setPath(req.getRequestURI());
        log.setMethod(req.getMethod());
        log.setStatus(res.getStatus());
        log.setDuration(duration);
        log.setTimestamp(LocalDateTime.now());
        repo.save(log);
        }
}
