package ru.job4j.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class FilterTrim implements Filter {
    private FilterConfig filterConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        LOGGER.info("Фильтр по обрезке пробелов в начале и в конце инициализирован");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String newLogin = request.getParameter("newlogin");
        String email = request.getParameter("email");
        if (name != null) {
            request.setAttribute("name", name.trim());
        } else {
            request.setAttribute("name", "");
        }
        if (login != null) {
            request.setAttribute("login", login.trim());
        } else {
            request.setAttribute("login", "");
        }
        if (newLogin != null) {
            request.setAttribute("newlogin", newLogin.trim());
        } else {
            request.setAttribute("newlogin", "");
        }
        if (email != null) {
            request.setAttribute("email", email.trim());
        } else {
            request.setAttribute("email", "");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("Фильтр по обрезке пробелов в начале и в конце деинициализирован");
        filterConfig = null;
    }
}
