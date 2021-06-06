package ru.ama.inwreaclaste;

import java.util.List;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public interface DbAccessor {
    //access user
    void addUser(User user);
    User getUser(String id);
    User getUserByLogin( String login );
    User getUserByEmail( String email );
    List<User> getUsers();
    void updateUser( User user );
    UserInfo getUserInfo( String id );
    UserInfo getUserInfoByLogin( String login );
    void updateUserInfo( String userId, UserInfo userInfo );

    //access chat
    String saveChatChannel( User userOne, User userTwo );
    String getChannelId( String userOneId, String userTwoId );
    List<ChatMessage> getChannelHistory( String channelId, int pageNumber, int pageSize );
    void saveMessage( String channelId, ChatMessage message );
}
