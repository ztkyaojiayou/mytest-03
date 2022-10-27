package com.sfauto.cloud.model.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

//@Configuration
public class CustomListenerErrorHandler {

    //@Bean
    public ConsumerAwareListenerErrorHandler listenerErrorHandler(){

        return new ConsumerAwareListenerErrorHandler() {
            public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
                return null;
            }

            public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
                return null; //return ConsumerAwareListenerErrorHandler.super.handleError(message, exception);
            }
        };
    }

}