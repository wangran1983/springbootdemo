package com.donbala.filter;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "sessionFilter", urlPatterns = {"/controller/*","/main/*","/pagedemo/*"})
public class SessionFilter implements Filter {

    public final static Logger log = (Logger) LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();

        String url = httpServletRequest.getRequestURI();
        if (!url.equals("/login")&&session != null && session.getAttribute("user") == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }
}
