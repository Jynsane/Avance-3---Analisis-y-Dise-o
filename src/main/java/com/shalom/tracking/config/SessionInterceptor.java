package com.shalom.tracking.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        // Permitir acceso a la pantalla de login, procesamiento de login, logout y búsqueda de tracking pública
        if (path.equals("/login") || path.equals("/logout") || path.startsWith("/tracking/public") || path.equals("/") ||
                path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/images/")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuarioLogueado") != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
