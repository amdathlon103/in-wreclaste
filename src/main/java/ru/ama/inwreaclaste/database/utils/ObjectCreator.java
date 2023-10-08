package ru.ama.inwreaclaste.database.utils;

import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.UserInfo;
import ru.ama.inwreaclaste.chat.ChatMessage;
import ru.ama.inwreaclaste.database.entities.BaseUser;
import ru.ama.inwreaclaste.database.entities.UserInfoEntity;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class ObjectCreator {

    private ObjectCreator() {}

    public static User createFrom( BaseUser baseUser ) {
        return new User( baseUser.id, baseUser.login, baseUser.password,
                         baseUser.email, baseUser.role, baseUser.registrationTime );
    }

    public static BaseUser createFrom( User user ) {
        return new BaseUser( user.id, user.login, user.password, user.email, user.role, user.registrationTime );
    }

    public static UserInfo createFrom( UserInfoEntity userInfoEntity ) {
        return new UserInfo( userInfoEntity.userId, userInfoEntity.name, userInfoEntity.status, userInfoEntity.dateOfBirth,
                             userInfoEntity.homeTown, userInfoEntity.instagramLink, userInfoEntity.vkLink,
                             userInfoEntity.customUrls, userInfoEntity.music, userInfoEntity.about, userInfoEntity.lastUpdated );
    }

    public static UserInfoEntity createFrom( UserInfo userInfo ) {
        return new UserInfoEntity( userInfo.userId, userInfo.name, userInfo.status, userInfo.dateOfBirth,
                                   userInfo.homeTown, userInfo.instagramLink, userInfo.vkLink,
                                   userInfo.customUrls, userInfo.music, userInfo.about, userInfo.lastUpdated );
    }

    public static ChatMessage createFrom( ru.ama.inwreaclaste.database.entities.ChatMessage message,
                                          BaseUser userOne, BaseUser userTwo )
    {
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
