package com.java.alami.utils;

import java.io.IOException;

public interface KafkaConsumerService {
    void consume(String message) throws IOException;
}
