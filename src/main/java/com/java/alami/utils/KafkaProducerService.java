package com.java.alami.utils;

public interface KafkaProducerService {
    void sendMessage(String topic, String message);
}
