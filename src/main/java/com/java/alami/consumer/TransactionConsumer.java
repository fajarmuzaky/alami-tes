package com.java.alami.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.alami.dto.TransactionLogsDto;
import com.java.alami.services.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class TransactionConsumer {

    private final MongoService transactionHistoryService;

    public TransactionConsumer(MongoService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @KafkaListener(topics = "transaction_detail", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("consume transaction dto from event: {}", message);
        try {
            ObjectMapper mapper = new ObjectMapper();
            TransactionLogsDto transactionLogsDto = mapper.readValue(message, TransactionLogsDto.class);
            transactionHistoryService.create(transactionLogsDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
