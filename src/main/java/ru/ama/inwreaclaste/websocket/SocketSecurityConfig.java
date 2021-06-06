package ru.ama.inwreaclaste.websocket;

import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

//@Configuration
public class SocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

//    @Override
//    protected boolean sameOriginDisabled() {
//        return true;
//    }
//
//    @Override
//    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
//        messages
//                .simpDestMatchers("/secured/**", "/secured/**/**").authenticated()
//                .anyMessage().authenticated();
//    }
}
