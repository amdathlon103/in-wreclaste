package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.DbAccessor;
import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.rest.dto.CustomResponse;
import ru.ama.inwreaclaste.rest.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/admin"  )
public class AdminEndpoint {

    private final DbAccessor dbAccessor;

    @Autowired
    public AdminEndpoint( DbAccessor dbAccessor ) {
        this.dbAccessor = dbAccessor;
    }

    @GetMapping( "/getList" )
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = dbAccessor.getUsers();
//        users.add(new User(1, "debt", "12345", "debt@debt.com", UserRoleEnum.ADMIN));
//        users.add(new User(2, "debt1", "12345", "debt@debt.com",UserRoleEnum.ADMIN));
//        users.add(new User(3, "debt2", "12345", "debt@debt.com",UserRoleEnum.USER));
//        users.add(new User(4, "debt3", "12345", "debt@debt.com",UserRoleEnum.ADMIN));
//        context.close();
        return new ResponseEntity<>( users, HttpStatus.OK );
    }

    @PostMapping( "/swap" )
    public ResponseEntity<CustomResponse<String>> setAdmin( @RequestBody UserDto userRq ) {
        User user = dbAccessor.getUserByLogin( userRq.login );
        if ( user == null )
            return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"User not found"}, 3 ), HttpStatus.OK );
        switch ( user.role ) {
            case ADMIN:
                user = new User( user.id, user.login, user.password, user.email, User.Role.USER );
                break;
            case USER:
                user = new User( user.id, user.login, user.password, user.email, User.Role.ADMIN );
                break;
            default:
                return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"Roles error"}, 2 ), HttpStatus.OK );
        }
        dbAccessor.updateUser( user );
        return new ResponseEntity<>( new CustomResponse<>( null, null, 1 ), HttpStatus.OK );
    }
}
