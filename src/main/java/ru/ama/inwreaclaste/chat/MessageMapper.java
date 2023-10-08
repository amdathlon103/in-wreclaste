package ru.ama.inwreaclaste.chat;

import ru.ama.inwreaclaste.chat.websocket.dto.OutputMessage;

import java.util.ArrayList;
import java.util.List;

public class MessageMapper {

    public static List<OutputMessage> mapChatMessagesToOutputMessages( List<ChatMessage> chatMessages ){
        List<OutputMessage> outputMessages = new ArrayList<>();
        for(ChatMessage message : chatMessages){
            outputMessages.add(OutputMessage.of(message));
        }
        return outputMessages;
    }
}
