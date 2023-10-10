package ru.ama.inwreaclaste.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order( 1 )
public class CORSFilter implements Filter {

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain )
            throws IOException, ServletException
    {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader( "Access-Control-Allow-Origin", "http://localhost:3000" );
        response.setHeader( "Access-Control-Allow-Credentials", "true" );
        response.setHeader( "Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE" );
        response.setHeader( "Access-Control-Max-Age", "3600" );
        response.setHeader( "Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers, Cookie, Set-Cookie" );
        response.setHeader( "Access-Control-Expose-Headers", "logged, Set-Cookie" );
        chain.doFilter( req, res );
    }

    public void init( FilterConfig filterConfig ) {
    }

    public void destroy() {
    }
}