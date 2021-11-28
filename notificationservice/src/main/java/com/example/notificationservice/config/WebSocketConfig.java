package com.example.notificationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${env.webFrontOrigin}")
    private String webFrontOrigin;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("queue");
        registry.setApplicationDestinationPrefixes("/ws");
        registry.setUserDestinationPrefix("/users");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        System.out.println("#################################################################");
        System.out.println(webFrontOrigin);
        System.out.println("#################################################################");

        registry.addEndpoint("/alerts")
                .setAllowedOrigins(webFrontOrigin)
                .withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new UserInterceptor());
    }
}