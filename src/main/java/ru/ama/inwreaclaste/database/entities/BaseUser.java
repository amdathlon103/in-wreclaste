package ru.ama.inwreaclaste.database.entities;

import ru.ama.inwreaclaste.User;

import jakarta.persistence.*;

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

    public User.Role role;

    public BaseUser() {
    }

    public BaseUser( String id, String login, String password, String email, User.Role role ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public User.Role getRole() {
        return role;
    }

    public void setRole( User.Role role ) {
        this.role = role;
    }
}
