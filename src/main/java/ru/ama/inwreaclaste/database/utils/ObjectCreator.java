package ru.ama.inwreaclaste.database.utils;

import ru.ama.inwreaclaste.ChatMessage;
import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.UserInfo;
import ru.ama.inwreaclaste.database.entities.BaseUser;
import ru.ama.inwreaclaste.database.entities.UserWithInfo;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class ObjectCreator {

    private ObjectCreator() {}

    public static User createFrom( BaseUser baseUser ) {
        return new User( baseUser.id, baseUser.login, baseUser.password,
                         baseUser.email, baseUser.role );
    }

    public static BaseUser createFrom( User user ) {
        return new BaseUser( user.id, user.login, user.password, user.email, user.role );
    }

    public static UserInfo createUserInfoFrom( UserWithInfo userWithInfo ) {
        return new UserInfo( userWithInfo.name, userWithInfo.status, userWithInfo.block1, userWithInfo.dob,
                             userWithInfo.htown, userWithInfo.block2, userWithInfo.instagram, userWithInfo.vk,
                             userWithInfo.url1, userWithInfo.urlname1, userWithInfo.url2, userWithInfo.urlname2,
                             userWithInfo.block3, userWithInfo.music, userWithInfo.about );
    }

    public static ChatMessage createFrom( ru.ama.inwreaclaste.database.entities.ChatMessage message,
                                          BaseUser userOne, BaseUser userTwo ) {
        if ( userOne.id.equals( message.senderId ) )
            return new ChatMessage( createFrom( userOne ), createFrom( userTwo ), message.contents, message.time );
        else
            return new ChatMessage( createFrom( userTwo ), createFrom( userOne ), message.contents, message.time );
    }

    public static ru.ama.inwreaclaste.database.entities.ChatMessage createFrom( ChatMessage message ) {
        return new ru.ama.inwreaclaste.database.entities.ChatMessage( message.id, message.sender.id, message.recipient.id,
                                                                      message.contents, message.time );
    }
}
