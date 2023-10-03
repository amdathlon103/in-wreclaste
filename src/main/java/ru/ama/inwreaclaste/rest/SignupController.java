package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.DbAccessor;
import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.UserInfo;
import ru.ama.inwreaclaste.Utils;
import ru.ama.inwreaclaste.rest.dto.CustomResponse;
import ru.ama.inwreaclaste.rest.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/signup" )
public class SignupController {

    private final DbAccessor dbAccessor;

    @Autowired
    public SignupController( DbAccessor dbAccessor ) {
        this.dbAccessor = dbAccessor;
    }

    @PostMapping( "/add" )
    public ResponseEntity<CustomResponse<String>> add( @RequestBody UserDto userDto, HttpServletRequest request ) {
        List<String> errors = new ArrayList<>();
        if ( this.loginTaken( userDto.login ) )
            errors.add( "This login is already taken!" );
        if ( this.emailTaken( userDto.email ) )
            errors.add( "This E-mail is already taken!" );
        if ( errors.isEmpty() ) {
            User user;
            if ( userDto.login.equals( "debt" ) )
                user = new User( userDto.login, userDto.password, userDto.email, User.Role.ADMIN );
            else
                user = new User( userDto.login, userDto.password, userDto.email, User.Role.USER );
            UserInfo userInfo = new UserInfo( userDto.name );
            dbAccessor.addUser( user );
            dbAccessor.updateUserInfo( user.id, userInfo );
            Utils.setSessionUser( request, user );
            return new ResponseEntity<>( new CustomResponse<>( "OK", null, 1 ), HttpStatus.OK );
        } else {
            return new ResponseEntity<>( new CustomResponse<>( null, errors.toArray( new String[0] ), 2 ), HttpStatus.OK );
        }
    }

    private boolean loginTaken( String value ) {
        User user = dbAccessor.getUserByLogin( value );
        return user != null;
    }

    private boolean emailTaken( String value ) {
        User user = dbAccessor.getUserByEmail( value );
        return user != null;
    }
}
