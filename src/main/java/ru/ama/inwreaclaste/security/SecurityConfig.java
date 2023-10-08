package ru.ama.inwreaclaste.security;

import ru.ama.inwreaclaste.Role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        return http
                   .authorizeHttpRequests( customizer -> customizer
                                                             .requestMatchers( "/test/login" ).permitAll()
                                                             .requestMatchers( "/test/add" ).permitAll()
                                                             .requestMatchers( "/user/login" ).permitAll()
                                                             .requestMatchers( "/user/register" ).permitAll()
                                                             .requestMatchers( "/user/**" ).hasAnyAuthority( Role.USER.name(), Role.ADMIN.name() )
                                                             .requestMatchers( "/admin/**" ).hasAuthority( Role.ADMIN.name() )
                                                             .requestMatchers( "/**" ).denyAll()
                                                             .anyRequest().denyAll() )
                   .exceptionHandling( customizer -> customizer
                                                         .authenticationEntryPoint( new HttpStatusEntryPoint( HttpStatus.UNAUTHORIZED ) ) )
                   .csrf( AbstractHttpConfigurer::disable )
                   .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService( PasswordEncoder passwordEncoder ) {
//        UserDetails user = User.withUsername( "admin" )
//                               .password( passwordEncoder.encode( "password" ) )
//                               .roles( "ADMIN" )
//                               .build();
//        return new InMemoryUserDetailsManager( user );
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
//        http.authorizeHttpRequests( ( authorizeHttpRequests ) -> authorizeHttpRequests.requestMatchers( "/**" )
//                                                                                      .hasRole( "ADMIN" ) )
//            .httpBasic( Customizer.withDefaults() );
//        return http.build();
//    }
//
}
