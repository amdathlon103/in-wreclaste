package ru.ama.inwreaclaste.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class RestAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence( final HttpServletRequest request,
                          final HttpServletResponse response,
                          final AuthenticationException authException ) throws IOException {
        response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );

        PrintWriter writer = response.getWriter();
        writer.println( "HTTP Status 401 : " + authException.getMessage() );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName( "MY_TEST_REALM" );
        super.afterPropertiesSet();
    }
}
