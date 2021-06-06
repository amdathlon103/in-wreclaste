package ru.ama.inwreaclaste.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class FriendDto implements Serializable {

    private static final long serialVersionUID = 662344002453540359L;

    public final String id;
    public final String login;

    @JsonCreator
    public FriendDto( @JsonProperty( "id" ) String id,
                      @JsonProperty( "login" ) String login ) {
        this.id = id;
        this.login = login;
    }
}
