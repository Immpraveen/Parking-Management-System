package com.kpmg.parkingreservation.security.filter;

import com.kpmg.parkingreservation.security.context.UserContext;
import com.kpmg.parkingreservation.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserContext userContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            if (jwtUtil.validateToken(jwt)) {
                // Set authentication in context if needed
                Claims claims = jwtUtil.extractAllClaims(jwt);
                username = claims.getSubject();
                Integer empId = claims.get("empId", Integer.class);
                String empType = claims.get("empType", String.class);
                // Set user context
                userContext.setEmpId(empId);
                userContext.setEmpType(empType);
                userContext.setUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        try {
            chain.doFilter(request, response);
        } finally {
            userContext.clear(); // Clean up after request
        }
    }
}

