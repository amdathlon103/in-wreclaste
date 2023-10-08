package ru.ama.inwreaclaste.rest;

import ru.ama.inwreaclaste.User;
import ru.ama.inwreaclaste.chat.ChatMessage;
import ru.ama.inwreaclaste.chat.MessageMapper;
import ru.ama.inwreaclaste.chat.websocket.dto.InputMessage;
import ru.ama.inwreaclaste.chat.websocket.dto.OutputMessage;
import ru.ama.inwreaclaste.database.RegistryAccessor;
import ru.ama.inwreaclaste.rest.dto.ChatChannelDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( value = "/private-chat" )
public class ChatController {

    private final RegistryAccessor registryAccessor;

    @Autowired
    public ChatController( RegistryAccessor registryAccessor ) {
        this.registryAccessor = registryAccessor;
    }

    //
//    @MessageMapping("/sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
////        String time = new SimpleDateFormat("HH:mm").format(new Date());
////        return new OutputMessage(message.getFrom(), message.getText(), time);
//        return chatMessage;
//    }
//
//    @MessageMapping("/addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }
//
//    @MessageMapping(SECURED_CHAT)
//    @SendTo(SECURED_CHAT_HISTORY)
//    public Message sendAll(Message msg) throws Exception {
////        OutputMessage out = new OutputMessage(msg.getFrom(), msg.getText(), new SimpleDateFormat("HH:mm").format(new Date()));
//        return msg;
//    }
//
//    /**
//     * Example of sending message to specific user using 'convertAndSendToUser()' and '/queue'
//     */
//    @MessageMapping(SECURED_CHAT_ROOM)
//    public void sendSpecific(@Payload Message msg, Principal user, @Header("simpSessionId") String sessionId) throws Exception {
////        OutputMessage out = new OutputMessage(msg.getFrom(), msg.getText(), LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)));
//        simpMessagingTemplate.convertAndSendToUser(msg.getTo(), SECURED_CHAT_SPECIFIC_USER, msg);
//    }

    @MessageMapping( "/private.chat.{channelId}" )
    @SendTo( "/topic/private.chat.{channelId}" )
    public OutputMessage chatMessage( @DestinationVariable String channelId, InputMessage inputMessage ) {
        User sender = registryAccessor.getUserByLogin( inputMessage.sender );
        User recipient = registryAccessor.getUserByLogin( inputMessage.recipient );

        ChatMessage chatMessage = new ChatMessage( sender, recipient, inputMessage.contents, new Date() );

        registryAccessor.saveMessage( channelId, chatMessage );

        return OutputMessage.of( chatMessage );
    }

    @PostMapping( "/channel" )
    public ResponseEntity<String> establishChatChannel( @RequestBody ChatChannelDto chatChannel ) {
        //TODO изменить на использование пользователя сессии и его собеседника
        String channelUuid = establishChatSession( chatChannel );

        return new ResponseEntity<>( channelUuid, HttpStatus.OK );
    }

    public String establishChatSession( ChatChannelDto chatChannel ) {
        User userOne = registryAccessor.getUserByLogin( chatChannel.userOneFullName );
        User userTwo = registryAccessor.getUserByLogin( chatChannel.userTwoFullName );
        String uuid = registryAccessor.getChannelId( userOne.id, userTwo.id );

        // If channel doesn't already exist, create a new one
        return ( uuid != null ) ? uuid : registryAccessor.saveChatChannel( userOne, userTwo );
    }

    @GetMapping( "/channel/{channelUuid}" )
    public ResponseEntity<List<OutputMessage>> getExistingChatMessages( @PathVariable( "channelUuid" ) String channelUuid ) {
        List<ChatMessage> chatMessages = registryAccessor.getChannelHistory( channelUuid, 0, 100 );

        List<OutputMessage> outputMessages = MessageMapper.mapChatMessagesToOutputMessages( chatMessages );

        return new ResponseEntity<>( outputMessages, HttpStatus.OK );
    }
}
