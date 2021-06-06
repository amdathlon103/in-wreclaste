package ru.ama.inwreaclaste.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class ChatChannelDto implements Serializable {

    private static final long serialVersionUID = -3441391941084703785L;

    public final String userOneFullName;
    public final String userTwoFullName;

    @JsonCreator
    public ChatChannelDto( @JsonProperty( "userOneFullName" ) String userOneFullName,
                           @JsonProperty( "userTwoFullName" ) String userTwoFullName ) {
        this.userOneFullName = userOneFullName;
        this.userTwoFullName = userTwoFullName;
    }
}
