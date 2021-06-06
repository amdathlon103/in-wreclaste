package ru.ama.inwreaclaste.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class UserRs implements Serializable {

    private static final long serialVersionUID = -1871127809001740474L;

    public final String id;
    public final String login;
    public final String password;
    public final String email;

    @JsonCreator
    public UserRs( @JsonProperty( "id" ) String id,
                   @JsonProperty( "login" ) String login,
                   @JsonProperty( "password" ) String password,
                   @JsonProperty( "email" ) String email ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
