package com.java.alami.utils;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @KafkaListener(topics = "USERS", groupId = "group_id")
    @Override
    public void consume(String message) throws IOException {
        System.out.println("### CONSUME DATA => " + message);
    }
}
