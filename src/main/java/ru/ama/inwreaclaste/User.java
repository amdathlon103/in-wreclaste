package ru.ama.inwreaclaste;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.ZonedDateTime;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class User {

    public String id;
    public String login;
    public String password;
    public String email;
    public Role role;
    public ZonedDateTime registrationTime;

    public User( String id, String login, String password, String email, Role role, ZonedDateTime registrationTime ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.registrationTime = registrationTime;
    }

    public User( String login, String password, String email, Role role ) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.registrationTime = ZonedDateTime.now();
    }
}
