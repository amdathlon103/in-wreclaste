package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.DbAccessor;
import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.Utils;
import ru.ama.inwreaclaste.rest.dto.CustomResponse;
import ru.ama.inwreaclaste.rest.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping( value = "/login"  )
public class LoginController {

    private final DbAccessor dbAccessor;

    @Autowired
    public LoginController( DbAccessor dbAccessor ) {
        this.dbAccessor = dbAccessor;
    }

    @PostMapping( "/user" )
    public ResponseEntity<CustomResponse<String>> login( @RequestBody UserDto userRq, HttpServletRequest request ) {
        String role;
        User user = dbAccessor.getUserByLogin( userRq.login );
        if ( user == null )
            return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"User not found"}, 2 ), HttpStatus.OK );
        role = user.role.name();
        Utils.setSessionUser( request, user );
        return new ResponseEntity<>( new CustomResponse<>( role, null, 1 ), HttpStatus.OK );
    }

    @GetMapping( "/username" )
    public String getUsername( HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session != null )
            return (String) session.getAttribute( "login" );
        return null;
    }
}
