package ru.ama.inwreaclaste;

import ru.ama.inwreaclaste.security.AuthUser;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public class Utils {

    @Nullable
    public static AuthUser getAuthenticatedUser( HttpServletRequest request ) {
        var auth = (Authentication) request.getUserPrincipal();
        if ( auth == null )
            return null;
        return (AuthUser) auth.getPrincipal();
    }
}
