package ru.ama.inwreaclaste.ignite;

import ru.ama.inwreaclaste.ChatMessage;
import ru.ama.inwreaclaste.DbAccessor;
import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.UserInfo;
import ru.ama.inwreaclaste.ignite.config.CacheConfig;
import ru.ama.inwreaclaste.ignite.dao.ChatCacheDaoImpl;
import ru.ama.inwreaclaste.ignite.dao.UserCacheDaoImpl;
import ru.ama.inwreaclaste.ignite.entities.ChannelWithMessages;
import ru.ama.inwreaclaste.ignite.entities.UserWithInfo;
import ru.ama.inwreaclaste.ignite.utils.ObjectCreator;
import ru.ama.inwreaclaste.ignite.utils.SearchHelper;

import org.apache.ignite.cache.query.ScanQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
@Service
@ConditionalOnBean( CacheConfig.class )
public class IgniteAccessor implements DbAccessor {

    private static final String ERROR_JSON_PROCESSING = "error json processing";
    private static final Logger LOGGER = LoggerFactory.getLogger( IgniteAccessor.class );

    private final UserCacheDaoImpl<String, UserWithInfo> userCacheDao;
    private final ChatCacheDaoImpl<String, ChannelWithMessages> chatCacheDao;

    public IgniteAccessor( UserCacheDaoImpl<String, UserWithInfo> userCacheDao,
                           ChatCacheDaoImpl<String, ChannelWithMessages> chatCacheDao ) {
        this.userCacheDao = userCacheDao;
        this.chatCacheDao = chatCacheDao;
    }

    @Override
    public void addUser( User user ) {
        LOGGER.debug( "insert user {}", user.id );
        userCacheDao.save( ObjectCreator.createFrom( user ) );
    }

    @Override
    public User getUser( String id ) {
        LOGGER.debug( "get user by id {}", id );
        return userCacheDao.findById( id )
                           .map( ObjectCreator::createFrom )
                           .orElse( null );
    }

    @Override
    public User getUserByLogin( String login ) {
        List<User> users = (List<User>) userCacheDao.getAll( new ScanQuery<>(
                                                                     ( k, user ) -> user.login.equals( login ) ),
                                                             entry -> ObjectCreator.createFrom( entry.getValue() ) );
        return users.size() > 0 ? users.get( 0 ) : null;
    }

    @Override
    public User getUserByEmail( String email ) {
        List<User> users = (List<User>) userCacheDao.getAll( new ScanQuery<>(
                                                                     ( k, user ) -> user.login.equals( email ) ),
                                                             entry -> ObjectCreator.createFrom( entry.getValue() ) );
        return users.size() > 0 ? users.get( 0 ) : null;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userCacheDao.getAll( new ScanQuery<>(),
                                                 entry -> ObjectCreator.createFrom( entry.getValue() ) );
    }

    @Override
    public void updateUser( User user ) {
        UserWithInfo userWithInfo = userCacheDao.findById( user.id ).orElse( null );
        if ( userWithInfo != null ) {
            userWithInfo.password = user.password;
            userWithInfo.email = user.email;
            userWithInfo.role = user.role;
            userCacheDao.update( userWithInfo );
        }
    }

    @Override
    public UserInfo getUserInfo( String id ) {
        LOGGER.debug( "get user by id {}", id );
        return userCacheDao.findById( id )
                           .map( ObjectCreator::createUserInfoFrom )
                           .orElse( null );
    }

    @Override
    public UserInfo getUserInfoByLogin( String login ) {
        List<UserInfo> users =
                (List<UserInfo>) userCacheDao.getAll( new ScanQuery<>(
                                                              ( k, user ) -> user.login.equals( login ) ),
                                                      entry -> ObjectCreator.createUserInfoFrom( entry.getValue() ) );
        return users.size() > 0 ? users.get( 0 ) : null;
    }

    @Override
    public void updateUserInfo( String userId, UserInfo userInfo ) {
        UserWithInfo userWithInfo = userCacheDao.findById( userId ).orElse( null );
        if ( userWithInfo != null ) {
            userWithInfo.name = userInfo.name;
            userWithInfo.status = userInfo.status;
            userWithInfo.block1 = userInfo.block1;
            userWithInfo.dob = userInfo.dob;
            userWithInfo.htown = userInfo.htown;
            userWithInfo.block2 = userInfo.block2;
            userWithInfo.instagram = userInfo.instagram;
            userWithInfo.vk = userInfo.vk;
            userWithInfo.url1 = userInfo.url1;
            userWithInfo.urlname1 = userInfo.urlname1;
            userWithInfo.url2 = userInfo.url2;
            userWithInfo.urlname2 = userInfo.urlname2;
            userWithInfo.block3 = userInfo.block3;
            userWithInfo.music = userInfo.music;
            userWithInfo.about = userInfo.about;
            userCacheDao.update( userWithInfo );
        }
    }

    @Override
    public String saveChatChannel( User userOne, User userTwo ) {
        var channelId = UUID.randomUUID().toString();
        ChannelWithMessages channel = new ChannelWithMessages( channelId, userOne.id, userTwo.id );
        chatCacheDao.save( channel );
        return channelId;
    }

    @Override
    public String getChannelId( String userOneId, String userTwoId ) {
        List<ChannelWithMessages> channels =
                chatCacheDao.getAll( new ScanQuery<>(
                                             ( k, channel ) -> SearchHelper.withUsers( channel, userOneId, userTwoId ) )
                                   );
        return channels.size() > 0 ? channels.get( 0 ).uuid : null;
    }

    @Override
    public List<ChatMessage> getChannelHistory( String channelId, int pageNumber, int pageSize ) {
        ChannelWithMessages channel = chatCacheDao.findById( channelId ).orElse( null );
        if ( channel != null && pageNumber >= 0 ) {
            UserWithInfo userOne = userCacheDao.findById( channel.userOneId ).get();
            UserWithInfo userTwo = userCacheDao.findById( channel.userTwoId ).get();
            var pageStartIndex = pageNumber * pageSize;
            if ( pageStartIndex < channel.messages.size() ) {
                var pageEndIndex = Math.min( pageStartIndex + pageSize, channel.messages.size() );
                return channel.messages.subList( pageStartIndex, pageEndIndex ).stream()
                                       .map( message -> ObjectCreator.createFrom( message, userOne, userTwo ) )
                                       .collect( Collectors.toList() );
            } else
                return Collections.emptyList();
        }
        return Collections.emptyList();
    }

    @Override
    public void saveMessage( String channelId, ChatMessage message ) {
        ChannelWithMessages channel = chatCacheDao.findById( channelId ).orElse( null );
        if ( channel == null )
            channel = new ChannelWithMessages( channelId, message.sender.id, message.recipient.id );
        channel.messages.add( ObjectCreator.createFrom( message ) );
        chatCacheDao.update( channel );
    }
}
