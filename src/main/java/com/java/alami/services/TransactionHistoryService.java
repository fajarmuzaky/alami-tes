package com.java.alami.services;

import com.java.alami.dao.Mongo.LogTransactionRepository;
import com.java.alami.dto.TransactionDto;
import com.java.alami.dto.TransactionLogsDto;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionHistoryService {

    @Autowired
    private LogTransactionRepository transactionHistoryRepository;

//    public TransactionHistory createTransactionHistory(TransactionLogsDto transactionLogsDto) {
//        TransactionLogsDto transaction = TransactionLogsDto.create(transactionLogsDto);
//        return transactionHistoryRepository.save(transaction);
//    }
}
