package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.Role;
import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.Utils;
import ru.ama.inwreaclaste.database.RegistryAccessor;
import ru.ama.inwreaclaste.error.AppException;
import ru.ama.inwreaclaste.error.AppExceptionCode;
import ru.ama.inwreaclaste.rest.dto.Dto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/user" )
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger( UserController.class );

    @Autowired
    private RegistryAccessor registryAccessor;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping( "/login" )
    public Dto.CurrentUser login( @RequestBody Dto.Login loginRq, HttpServletRequest request ) {
        try {
            if ( request.getUserPrincipal() == null )
                request.login( loginRq.login(), loginRq.password() );
        } catch ( ServletException e ) {
            throw new AppException( AppExceptionCode.USER_LOGIN_ERROR );
        }

        var user = Validate.notNull( Utils.getAuthenticatedUser( request ) );
        LOGGER.info( "User {} logged in.", user.getUsername() );

        return new Dto.CurrentUser( user.getId(), user.getUsername() );
    }

    @PostMapping( "/register" )
    public Dto.RegisteredUser register( @RequestBody Dto.Register registerRq,
                                        HttpServletRequest request ) throws ServletException
    {
        boolean newUser = false;
        if ( Utils.getAuthenticatedUser( request ) == null ) {
            validateLoginAndEmail( registerRq );

            User user = new User( registerRq.login(), passwordEncoder.encode( registerRq.password() ),
                                  registerRq.email(), Role.USER );
            registryAccessor.addUser( user );
            request.login( registerRq.login(), registerRq.password() );
            newUser = true;
        }
        var user = Validate.notNull( Utils.getAuthenticatedUser( request ) );

        return new Dto.RegisteredUser( user.getId(), user.getUsername(), newUser );
    }

    private void validateLoginAndEmail( Dto.Register registerRq ) {
        if ( this.loginTaken( registerRq.login() ) )
            throw new AppException( AppExceptionCode.ALREADY_TAKEN_REGISTRATION_ERROR, "login" );

        if ( this.emailTaken( registerRq.email() ) )
            throw new AppException( AppExceptionCode.ALREADY_TAKEN_REGISTRATION_ERROR, "email" );
    }

    private boolean loginTaken( String value ) {
        var user = registryAccessor.getUserByLogin( value );
        return user != null;
    }

    private boolean emailTaken( String value ) {
        var user = registryAccessor.getUserByEmail( value );
        return user != null;
    }

    @PostMapping( "/populateCurrentUser" )
    public Dto.UserInfo populateCurrentUser( @RequestBody Dto.UserInfo userInfoRq, HttpServletRequest request ) {
        var user = Validate.notNull( Utils.getAuthenticatedUser( request ) );
        var userInfo = Dto.createFrom( userInfoRq, user.getId() );
        return Dto.createFrom( registryAccessor.updateUserInfo( userInfo ) );
    }

    @GetMapping( "/current" )
    public Dto.CurrentUser getUsername( HttpServletRequest request ) {
        var user = Validate.notNull( Utils.getAuthenticatedUser( request ) );
        return new Dto.CurrentUser( user.getId(), user.getUsername() );
    }
}
