package com.java.alami.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.constants.TransactionTypes;
import com.java.alami.entity.Member;
import com.java.alami.entity.Transaction;
import com.java.alami.entity.TransactionLogs;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionLogsDto {
    private Long id;
    private Long memberId;
    private String memberName;
    private String transactionType;
    private Integer amount;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date created_at;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date updated_at;

    public TransactionLogsDto() {}

    public TransactionLogsDto(Long id, Long memberId, String memberName, int transactionType, Integer amount, Date created_at){
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.transactionType = TransactionTypes.getTransactionTypeName(transactionType);
        this.amount = amount;
        this.created_at = created_at;
    }

    public static TransactionLogsDto create(TransactionLogs transactionLogs){
        TransactionLogsDto transactionLogsDto = new TransactionLogsDto();
        transactionLogsDto.setId(transactionLogs.getId());
        transactionLogsDto.setMemberId(transactionLogs.getMemberId());
        transactionLogsDto.setMemberName(transactionLogs.getMemberName());
        transactionLogsDto.setTransactionType(TransactionTypes.getTransactionTypeName(transactionLogs.getTransactionType()));
        transactionLogsDto.setAmount(transactionLogs.getAmount());
        transactionLogsDto.setCreated_at(transactionLogs.getCreated_at());
        transactionLogsDto.setUpdated_at(transactionLogs.getUpdated_at());
        return transactionLogsDto;
    }
}
