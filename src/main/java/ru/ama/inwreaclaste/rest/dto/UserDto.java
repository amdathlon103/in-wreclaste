package ru.ama.inwreaclaste.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = -9080911887422414047L;

    public final String login;
    public final String password;
    public final String email;
    public final String name;

    @JsonCreator
    public UserDto( @JsonProperty("login") String login,
                    @JsonProperty("password") String password,
                    @JsonProperty("email") String email,
                    @JsonProperty("name") String name ) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
    }
}
