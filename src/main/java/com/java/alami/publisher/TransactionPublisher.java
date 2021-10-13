package com.java.alami.publisher;

import com.java.alami.dto.TransactionDto;
import com.java.alami.dto.TransactionLogsDto;
import com.java.alami.entity.TransactionLogs;
import com.java.alami.utils.KafkaPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static  com.java.alami.utils.ResponseUtil.toJson;

@Slf4j
@Component
public class TransactionPublisher {

    @Autowired
    private KafkaPublisherService kafkaPublisherService;

    public void publish(TransactionLogsDto transactionLogsDto) {
        log.info("publish transaction dto into event: {}", toJson(transactionLogsDto));
        kafkaPublisherService.sendMessage("transaction_detail", toJson(transactionLogsDto));
    }
}
