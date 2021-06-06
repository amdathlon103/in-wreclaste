package ru.ama.inwreaclaste.ignite.utils;

import ru.ama.inwreaclaste.ignite.entities.ChannelWithMessages;

/**
 * @author AmaJohny, amdathlon103@gmail.com
 * created 06.06.2021
 */
public class SearchHelper {

    public static boolean withUsers( ChannelWithMessages channel, String userOneId, String userTwoId ){
        return (channel.userOneId.equals( userOneId ) && channel.userTwoId.equals( userTwoId )) ||
               (channel.userOneId.equals( userTwoId ) && channel.userTwoId.equals( userOneId ));
    }
}
