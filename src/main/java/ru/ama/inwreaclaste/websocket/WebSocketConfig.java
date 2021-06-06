package ru.ama.inwreaclaste.websocket;

import ru.ama.inwreaclaste.Constants;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints( StompEndpointRegistry registry ) {
        registry.addEndpoint( "/ws" ).setAllowedOrigins( "*" ).withSockJS();
        registry.addEndpoint( Constants.SECURED_CHAT_ROOM ).setAllowedOrigins( "*" ).withSockJS();
        registry.addEndpoint( Constants.SECURED_CHAT ).setAllowedOrigins( "*" ).withSockJS();
    }

    @Override
    public void configureMessageBroker( MessageBrokerRegistry registry ) {
        registry.setApplicationDestinationPrefixes( "/app" );
        registry.enableSimpleBroker( Constants.SECURED_CHAT_HISTORY, Constants.SECURED_CHAT_SPECIFIC_USER,
                                     "/topic/", "/queue/" );
        registry.setUserDestinationPrefix( "/secured/user" );
//        registry.enableStompBrokerRelay("/topic/", "/queue/").setRelayHost("192.168.1.88")
//                .setRelayPort(61613);
    }

    protected void configureInbound( MessageSecurityMetadataSourceRegistry messages ) {
        messages.simpDestMatchers( "/*" ).authenticated();
    }
}
