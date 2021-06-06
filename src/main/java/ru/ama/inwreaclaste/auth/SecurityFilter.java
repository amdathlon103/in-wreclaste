package ru.ama.inwreaclaste.auth;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 07.06.2021
 */
@Component
@Order( 2 )
public class SecurityFilter extends HttpFilter {

    @Override
    protected void doFilter( HttpServletRequest request, HttpServletResponse response, FilterChain chain )
            throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        chain.doFilter( request, response );
        response.getHeaderNames();
    }
}
