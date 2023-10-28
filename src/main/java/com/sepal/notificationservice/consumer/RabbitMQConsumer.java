package com.sepal.notificationservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQConsumer.class);

    //Consumer is subscribed to the Queue
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(String message){
        LOGGER.info(String .format("Message Received --> %s", message));
    }
}
