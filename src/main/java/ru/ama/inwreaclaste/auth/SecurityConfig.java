package ru.ama.inwreaclaste.auth;

import ru.ama.inwreaclaste.DbAccessor;
import ru.ama.inwreaclaste.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String REALM = "MY_TEST_REALM";

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService( userDetailsService() ).passwordEncoder( encoder );
    }

    @Bean
    public RestAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        final DaoAuthenticationProvider bean = new CustomDaoAuthenticationProvider();
//        bean.setUserDetailsService(getUserDetailsService());
//        bean.setPasswordEncoder(encoder());
//        return bean;
//    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName( "JSESSIONID" );
        serializer.setCookiePath( "/" );
        serializer.setDomainNamePattern( "^.+?\\.(\\w+\\.[a-z]+)$" );
        serializer.setUseSecureCookie( true );
        serializer.setSameSite( "none" );
        return serializer;
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()//.anyRequest().authenticated()
                .antMatchers( "/login/user" ).authenticated()
                .antMatchers( "/login/username" ).permitAll()
                .antMatchers( "/signup/add" ).permitAll()
                .antMatchers( "/user/userinfo/**" ).permitAll()
                .antMatchers( "/user/getFriendList" ).permitAll()
                .antMatchers( "/admin/**" ).hasRole( User.Role.ADMIN.name() )
                .antMatchers( "/profile/edit" ).authenticated()
                .antMatchers( "/profile/update" ).authenticated()
                .antMatchers( "/profile/**" ).permitAll()
                .antMatchers( "/private-chat/channel" ).authenticated()
                .antMatchers( "/private-chat/channel/**" ).authenticated()
                .and()
                .httpBasic().realmName( REALM ).authenticationEntryPoint( getBasicAuthEntryPoint() )
                .and()
                .logout().logoutUrl( "/logout" ).invalidateHttpSession( true ).deleteCookies( "JSESSIONID" )
                .logoutSuccessHandler( new CustomLogoutSuccessHandler() );
    }

//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

    @Override
    public void configure( WebSecurity web ) throws Exception {
        web.ignoring()
           .antMatchers( HttpMethod.OPTIONS, "/**" )
           .antMatchers( "/resources/**" );
    }
}
