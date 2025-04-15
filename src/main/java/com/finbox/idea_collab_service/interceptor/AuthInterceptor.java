package com.finbox.idea_collab_service.interceptor;

import com.finbox.idea_collab_service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        if (token == null || token.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Missing token header");
            return false;
        }

        String employeeId = authService.authenticateAndGetEmployeeId(token); // Validate and extract employeeId

        if (employeeId == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid or expired token");
            return false;
        }

        System.out.println("Employee ID: " + employeeId); // Debugging line

        request.setAttribute("employeeId", employeeId);
        return true;
    }

}

