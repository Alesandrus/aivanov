package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр, отвечающий за аутентификацию пользователя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class Authentificator implements Filter {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная для сохранения настроек фильтра.
     */
    private FilterConfig filterConfig;

    /**
     * Сохранение конфигурации фильтра при инициализации фильтра.
     *
     * @param filterConfig кофигурация фильтра.
     * @throws ServletException .
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * Метод, определяющий авторизован ли пользователь. Если нет, то пользователь перенаправляется
     * на страницу авторизации.
     *
     * @param request  запрос.
     * @param response ответ.
     * @param chain    цепочка.
     * @throws IOException      .
     * @throws ServletException .
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (!httpServletRequest.getRequestURI().contains("/signin")) {
            HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute("login") == null) {
                res.sendRedirect(String.format("%s/signin", httpServletRequest.getContextPath()));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Метод выполняющийся завершении работы фильтра.
     */
    @Override
    public void destroy() {
    }
}
