package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.DbAccessor;
import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.Utils;
import ru.ama.inwreaclaste.rest.dto.CustomResponse;
import ru.ama.inwreaclaste.rest.dto.FriendDto;
import ru.ama.inwreaclaste.rest.dto.UserRs;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/user" )
public class UserController {

    private final DbAccessor dbAccessor;

    @Autowired
    public UserController( DbAccessor dbAccessor ) {
        this.dbAccessor = dbAccessor;
    }

    @GetMapping( "/userinfo/{login}" )
    public ResponseEntity<CustomResponse<UserRs>> getUser( @PathVariable String login, HttpServletRequest request ) {
        User user = dbAccessor.getUserByLogin( login );
        if ( user == null )
            return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"User not found"}, 2 ), HttpStatus.OK );
        HttpHeaders headers = new HttpHeaders();
        User sessionUser = Utils.getSessionUser( request );
        if ( sessionUser != null && sessionUser.login.equals( login ) ) {
            headers.add( "logged", "true" );
            return new ResponseEntity<>( new CustomResponse<>( new UserRs( sessionUser.id, user.login,
                                                                           user.password, user.email ),
                                                               null, 1 ),
                                         headers, HttpStatus.OK );
        }
        headers.add( "logged", "false" );
        return new ResponseEntity<>( new CustomResponse<>( new UserRs( "", user.login, "", "" ), null, 1 ), headers, HttpStatus.OK );
    }

    @GetMapping( "/getFriendList" )
    public ResponseEntity<CustomResponse<List<FriendDto>>> getFriends() {
        List<User> users = dbAccessor.getUsers();
        List<FriendDto> friends = new ArrayList<>();
        for ( User user : users ) {
            friends.add( new FriendDto( user.id, user.login ) );
        }
//        users.add(new User(1, "debt", "12345", "debt@debt.com", UserRoleEnum.ADMIN));
//        users.add(new User(2, "debt1", "12345", "debt@debt.com",UserRoleEnum.ADMIN));
//        users.add(new User(3, "debt2", "12345", "debt@debt.com",UserRoleEnum.USER));
//        users.add(new User(4, "debt3", "12345", "debt@debt.com",UserRoleEnum.ADMIN));
//        context.close();
        return new ResponseEntity<>( new CustomResponse<>( friends, null, 1 ), HttpStatus.OK );
    }
}
