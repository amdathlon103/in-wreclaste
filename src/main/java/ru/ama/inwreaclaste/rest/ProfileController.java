package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.DbAccessor;
import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.UserInfo;
import ru.ama.inwreaclaste.Utils;
import ru.ama.inwreaclaste.rest.dto.CustomResponse;
import ru.ama.inwreaclaste.rest.dto.UserInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
@RestController
@RequestMapping( value = "/profile" )
public class ProfileController {

    private final DbAccessor dbAccessor;

    @Autowired
    public ProfileController( DbAccessor dbAccessor ) {
        this.dbAccessor = dbAccessor;
    }

    @GetMapping( value = "/{login}" )
    public ResponseEntity<CustomResponse<UserInfo>> profileInfo( @PathVariable String login ) {
        UserInfo userInfo = dbAccessor.getUserInfoByLogin( login );
        if ( userInfo == null )
            return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"User not found"}, 3 ), HttpStatus.OK );
        return new ResponseEntity<>( new CustomResponse<>( userInfo, null, 1 ), HttpStatus.OK );
    }

    @GetMapping( value = "/edit" )
    public ResponseEntity<CustomResponse<UserInfo>> loadEditInfo( HttpServletRequest request ) {
        User sessionUser = Utils.getSessionUser( request );
        if ( sessionUser != null ) {
            UserInfo userInfo = dbAccessor.getUserInfo( sessionUser.id );
            if ( userInfo != null )
                return new ResponseEntity<>( new CustomResponse<>( userInfo, null, 1 ), HttpStatus.OK );
            return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"User not found"}, 2 ), HttpStatus.OK );
        }
        return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"No logged user"}, 3 ), HttpStatus.OK );
    }

    @PostMapping( value = "/update" )
    public ResponseEntity<CustomResponse<String>> updateInfo( @RequestBody UserInfoDto userInfoRq, HttpServletRequest request ) {
        User sessionUser = Utils.getSessionUser( request );
        if ( sessionUser != null ) {
            UserInfo userInfo = new UserInfo( userInfoRq );
            dbAccessor.updateUserInfo( sessionUser.id, userInfo );
            return new ResponseEntity<>( new CustomResponse<>( "OK", null, 1 ), HttpStatus.OK );
        }
        return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"No logged user"}, 3 ), HttpStatus.OK );
    }
}
