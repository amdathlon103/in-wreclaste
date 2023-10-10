package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.database.RegistryAccessor;
import ru.ama.inwreaclaste.rest.dto.Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( value = "/admin" )
public class AdminEndpoint {

    private final RegistryAccessor registryAccessor;

    @Autowired
    public AdminEndpoint( RegistryAccessor registryAccessor ) {
        this.registryAccessor = registryAccessor;
    }

    @GetMapping( "/getList" )
    public List<Dto.AdminUserInfo> getUsers() {
        return registryAccessor.getUsers()
                               .stream()
                               .map( Dto::createFrom )
                               .collect( Collectors.toList() );
    }

//    @PostMapping( "/swap" )
//    public ResponseEntity<CustomResponse<String>> setAdmin( @RequestBody UserDto userRq ) {
//        User user = registryAccessor.getUserByLogin( userRq.login );
//        if ( user == null )
//            return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"User not found"}, 3 ), HttpStatus.OK );
//        switch ( user.role ) {
//            case ADMIN:
//                user = new User( user.id, user.login, user.password, user.email, User.Role.USER );
//                break;
//            case USER:
//                user = new User( user.id, user.login, user.password, user.email, User.Role.ADMIN );
//                break;
//            default:
//                return new ResponseEntity<>( new CustomResponse<>( null, new String[]{"Roles error"}, 2 ), HttpStatus.OK );
//        }
//        registryAccessor.updateUser( user );
//        return new ResponseEntity<>( new CustomResponse<>( null, null, 1 ), HttpStatus.OK );
//    }
}
