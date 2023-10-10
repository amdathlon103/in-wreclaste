package ru.ama.inwreaclaste.security;

import ru.ama.inwreaclaste.database.BaseUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BaseUserRepository repository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        var user = repository.findByLogin( username )
                             .orElseThrow( () -> new UsernameNotFoundException( "Username " + username + " not found" ) );
        return new AuthUser( user.id, user.login, user.password, user.email, user.role );
    }
}
