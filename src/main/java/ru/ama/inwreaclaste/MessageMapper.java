package ru.ama.inwreaclaste;

import ru.ama.inwreaclaste.websocket.dto.OutputMessage;

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
