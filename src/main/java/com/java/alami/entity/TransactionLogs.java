package com.java.alami.entity;

import com.java.alami.dto.TransactionLogsDto;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Document(collection = "transaction_logs")
@Data
public class TransactionLogs {
    @Id
    private String id;

    @Column(name = "id_member")
    private Long memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    public static TransactionLogs mapperCreateLogs(TransactionLogsDto transactionLogsDto){
        TransactionLogs transactionLogs = new TransactionLogs();
        transactionLogs.setMemberId(transactionLogsDto.getMemberId());
        transactionLogs.setMemberName(transactionLogsDto.getMemberName());
        transactionLogs.setTransactionType(transactionLogsDto.getTransactionType());
        transactionLogs.setAmount((transactionLogsDto.getAmount()));
        transactionLogs.setCreated_at(transactionLogsDto.getCreated_at());
        return transactionLogs;
    }
}
