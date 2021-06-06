package ru.ama.inwreaclaste.auth;

import ru.ama.inwreaclaste.Constants;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess( HttpServletRequest request, HttpServletResponse response,
                                 Authentication authentication ) {
        HttpSession session = request.getSession();
        if ( session != null )
            session.removeAttribute( Constants.SESSION_USER );
    }
}
