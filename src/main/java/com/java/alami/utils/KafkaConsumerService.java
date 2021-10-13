package com.java.alami.utils;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "USERS", groupId = "group_id")
    public void consume(Object message) throws IOException {
        System.out.println("### CONSUME DATA => " + message.toString());
    }
}
