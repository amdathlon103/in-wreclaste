package ru.ama.inwreaclaste.database;

import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.UserInfo;
import ru.ama.inwreaclaste.chat.ChatMessage;
import ru.ama.inwreaclaste.database.entities.BaseUser;
import ru.ama.inwreaclaste.database.utils.ObjectCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistryAccessorService implements RegistryAccessor {

    @Autowired
    private BaseUserRepository baseUserRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void addUser( User user ) {
        BaseUser baseUser = ObjectCreator.createFrom( user );
        baseUserRepository.save( baseUser );
    }

    @Override
    public User getUser( String id ) {
        BaseUser baseUser = baseUserRepository.findById( id ).orElse( null );
        return baseUser == null ? null : ObjectCreator.createFrom( baseUser );
    }

    @Override
    public User getUserByLogin( String login ) {
        BaseUser baseUser = baseUserRepository.findByLogin( login ).orElse( null );
        return baseUser == null ? null : ObjectCreator.createFrom( baseUser );
    }

    @Override
    public User getUserByEmail( String email ) {
        BaseUser baseUser = baseUserRepository.findByEmail( email ).orElse( null );
        return baseUser == null ? null : ObjectCreator.createFrom( baseUser );
    }

    @Override
    public List<User> getUsers() {
        return baseUserRepository.findAll()
                                 .stream()
                                 .map( ObjectCreator::createFrom )
                                 .collect( Collectors.toList() );
    }

    @Override
    public void updateUser( User user ) {
        var baseUser = ObjectCreator.createFrom( user );
        baseUserRepository.save( baseUser );
    }

    @Override
    public UserInfo getUserInfo( String id ) {
        var userInfo = userInfoRepository.getReferenceById( id );
        return ObjectCreator.createFrom( userInfo );
    }

    @Override
    public UserInfo updateUserInfo( UserInfo userInfo ) {
        var userInfoEntity = ObjectCreator.createFrom( userInfo );
        return ObjectCreator.createFrom( userInfoRepository.save( userInfoEntity ) );
    }

    @Override
    public String saveChatChannel( User userOne, User userTwo ) {
        return null;
    }

    @Override
    public String getChannelId( String userOneId, String userTwoId ) {
        return null;
    }

    @Override
    public List<ChatMessage> getChannelHistory( String channelId, int pageNumber, int pageSize ) {
        return null;
    }

    @Override
    public void saveMessage( String channelId, ChatMessage message ) {

    }
}
