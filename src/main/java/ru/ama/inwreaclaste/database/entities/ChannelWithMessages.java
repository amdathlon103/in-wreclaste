package ru.ama.inwreaclaste.database.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class ChannelWithMessages {

//    @QuerySqlField( index = true, notNull = true )
    public String uuid;

//    @QuerySqlField( index = true, notNull = true )
    public String userOneId;

//    @QuerySqlField( index = true, notNull = true )
    public String userTwoId;

    public List<ChatMessage> messages;

    public ChannelWithMessages( String uuid, String userOneId, String userTwoId, List<ChatMessage> messages ) {
        this.uuid = uuid;
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
        this.messages = messages;
    }

    public ChannelWithMessages( String uuid, String userOneId, String userTwoId ) {
        this.uuid = uuid;
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
        this.messages = new ArrayList<>();
    }
}
