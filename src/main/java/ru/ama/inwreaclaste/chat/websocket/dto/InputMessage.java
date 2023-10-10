package ru.ama.inwreaclaste.chat.websocket.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class InputMessage implements Serializable {

    private static final long serialVersionUID = -1496609605228582707L;

    public final String sender;
    public final String recipient;
    public final String contents;

    @JsonCreator
    public InputMessage( @JsonProperty( "sender" ) String sender,
                         @JsonProperty( "recipient" ) String recipient,
                         @JsonProperty( "contents" ) String contents ) {
        this.sender = sender;
        this.recipient = recipient;
        this.contents = contents;
    }
}