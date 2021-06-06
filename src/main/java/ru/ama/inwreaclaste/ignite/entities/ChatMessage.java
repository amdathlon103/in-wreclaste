package ru.ama.inwreaclaste.ignite.entities;

import java.util.Date;
import java.util.UUID;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class ChatMessage {

    public String id;

    public String senderId;

    public String recipientId;

    public String contents;

    public Date time;

    public ChatMessage( String id, String senderId, String recipientId, String contents, Date time ) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.contents = contents;
        this.time = time;
    }
}
