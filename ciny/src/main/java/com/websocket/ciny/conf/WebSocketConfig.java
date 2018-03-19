package com.websocket.ciny.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * @Author: John
     * @Description: Config websocket mapping prefixs.
     * @Date: 12:25 2018/3/18
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * @Author: John
     * @Description: Config SockJS endpoints.
     * @Date: 12:25 2018/3/18
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Enable SockJS connects to websocket server.
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}