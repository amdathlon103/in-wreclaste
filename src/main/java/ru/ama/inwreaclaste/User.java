package ru.ama.inwreaclaste;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class User implements Serializable {

    public enum Role {
        ADMIN,
        USER,
        ANONYMOUS;
    }

    public String id;
    public String login;
    public String password;
    public String email;
    public Role role;

    public User( String id, String login, String password, String email, Role role ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User( String login, String password, String email, Role role ) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
