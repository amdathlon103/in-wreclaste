package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.UserInfo;
import ru.ama.inwreaclaste.Utils;
import ru.ama.inwreaclaste.database.RegistryAccessor;
import ru.ama.inwreaclaste.rest.dto.Dto;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
@RestController
@RequestMapping( value = "/profile" )
public class ProfileController {

    @Autowired
    private RegistryAccessor registryAccessor;

    @GetMapping( value = "/{login}" )
    public Dto.UserInfo profileInfo( @PathVariable String login ) {
        var user = registryAccessor.getUserByLogin( login );
        if ( user == null )
            return null;
        UserInfo userInfo = registryAccessor.getUserInfo( user.id );
        if ( userInfo == null )
            return null;
        return Dto.createFrom( userInfo );
    }

    @PostMapping( value = "/update" )
    public Dto.UserInfo updateInfo( @RequestBody Dto.UserInfo userInfoRq, HttpServletRequest request ) {
        var user = Validate.notNull( Utils.getAuthenticatedUser( request ) );
        var userInfo = Dto.createFrom( userInfoRq, user.getId() );
        return Dto.createFrom( registryAccessor.updateUserInfo( userInfo ) );
    }
}
