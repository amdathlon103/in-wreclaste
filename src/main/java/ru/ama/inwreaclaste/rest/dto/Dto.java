package ru.ama.inwreaclaste.rest.dto;

import ru.ama.inwreaclaste.User;

import java.time.ZonedDateTime;
import java.util.List;

public class Dto {

    public record Login( String login, String password ) {}

    public record CurrentUser( String id, String login ) {}

    public record Register( String login, String password, String email ) {}

    public record RegisteredUser( String id, String login, Boolean newUser ) {}

    public record UserInfo( String name, String status, String dateOfBirth, String homeTown, String instagramLink,
                            String vkLink, List<String> customUrls, String music, String about,
                            Long lastUpdated ) {}

    public record AdminUserInfo( String id, String login, String password, String email, Long registrationTime ) {}

    public static AdminUserInfo createFrom( User user ) {
        return new AdminUserInfo( user.id, user.login, user.password, user.email, user.registrationTime.toInstant().toEpochMilli() );
    }

    public static ru.ama.inwreaclaste.UserInfo createFrom( UserInfo userInfo, String userId ) {
        return new ru.ama.inwreaclaste.UserInfo( userId, userInfo.name(), userInfo.status(), userInfo.dateOfBirth(),
                                                 userInfo.homeTown(), userInfo.instagramLink(), userInfo.vkLink(),
                                                 userInfo.customUrls(), userInfo.music(), userInfo.about(),
                                                 ZonedDateTime.now() );
    }

    public static UserInfo createFrom( ru.ama.inwreaclaste.UserInfo userInfo ) {
        return new UserInfo( userInfo.name, userInfo.status, userInfo.dateOfBirth,
                             userInfo.homeTown, userInfo.instagramLink, userInfo.vkLink,
                             userInfo.customUrls, userInfo.music, userInfo.about, userInfo.lastUpdated.toInstant().toEpochMilli() );
    }
}
