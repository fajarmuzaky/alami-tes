package com.java.alami.dao.Mongo;


import com.java.alami.dto.TransactionLogsDto;
import com.java.alami.entity.TransactionLogs;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogTransactionRepository extends MongoRepository<TransactionLogs, Long> {
    List<TransactionLogs> findByMemberId(Long id_member);
}
