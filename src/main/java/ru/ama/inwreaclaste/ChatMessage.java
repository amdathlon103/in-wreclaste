package ru.ama.inwreaclaste;

import java.util.Date;
import java.util.UUID;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class ChatMessage {

    public String id;
    public User sender;
    public User recipient;
    public String contents;
    public Date time;

    public ChatMessage( User sender, User recipient, String contents, Date time ) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.recipient = recipient;
        this.contents = contents;
        this.time = time;
    }


}
