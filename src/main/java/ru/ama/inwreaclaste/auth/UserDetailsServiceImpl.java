package ru.ama.inwreaclaste.auth;

import ru.ama.inwreaclaste.DbAccessor;
import ru.ama.inwreaclaste.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DbAccessor dbAccessor;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername( String username ) {
        User user = dbAccessor.getUserByLogin( username );
        if ( user == null )
            return null;
        var enabled = true;
        var accountNonExpired = true;
        var credentialsNonExpired = true;
        var accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<>( 1 );
        authorities.add( new SimpleGrantedAuthority( "ROLE_" + user.role.name() ) );

        return new AuthUser( user.login, encoder.encode( user.password ), enabled, accountNonExpired,
                             credentialsNonExpired, accountNonLocked, authorities, user.id );
    }
}
