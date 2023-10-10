package ru.ama.inwreaclaste.database.entities;

import ru.ama.inwreaclaste.Role;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table( indexes = {
        @Index( columnList = "login", unique = true ),
        @Index( columnList = "email", unique = true )
} )
public class BaseUser {

    @Id
    public String id;

    @Column(unique = true, nullable = false)
    public String login;
    public String password;
    @Column(unique = true, nullable = false)
    public String email;
    public Role role;
    public ZonedDateTime registrationTime;

    public BaseUser() {
    }

    public BaseUser( String id, String login, String password, String email, Role role,
                     ZonedDateTime registrationTime ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.registrationTime = registrationTime;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
    }

    public ZonedDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime( ZonedDateTime registrationTime ) {
        this.registrationTime = registrationTime;
    }
}
