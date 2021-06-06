package ru.ama.inwreaclaste.websocket.dto;

import ru.ama.inwreaclaste.ChatMessage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class OutputMessage implements Serializable {

    private static final long serialVersionUID = -2756810160178183283L;

    public final String sender;
    public final String recipient;
    public final String contents;
    public final Date time;

    @JsonCreator
    public OutputMessage( @JsonProperty( "sender" ) String sender,
                          @JsonProperty( "recipient" ) String recipient,
                          @JsonProperty( "contents" ) String contents,
                          @JsonProperty( "time" ) Date time ) {
        this.sender = sender;
        this.recipient = recipient;
        this.contents = contents;
        this.time = time;
    }

    public static OutputMessage of( InputMessage inputMessage ) {
        return new OutputMessage( inputMessage.sender, inputMessage.recipient, inputMessage.contents, new Date());
    }

    public static OutputMessage of( ChatMessage message ) {
        return new OutputMessage(message.sender.login, message.recipient.login,
                                 message.contents, message.time);
    }
}