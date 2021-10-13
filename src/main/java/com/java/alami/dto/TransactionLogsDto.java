package com.java.alami.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.alami.constants.TransactionTypes;
import com.java.alami.entity.Member;
import com.java.alami.entity.Transaction;
import com.java.alami.entity.TransactionLogs;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionLogsDto {

//    @JsonProperty("id")
//    private Long id;

    @JsonProperty("member_id")
    private Long memberId;

    @JsonProperty("member_name")
    private String memberName;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("amount")
    private Integer amount;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    @JsonProperty("created_at")
    private Date created_at;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date updated_at;

    public TransactionLogsDto() {}

    public TransactionLogsDto(Long memberId, String memberName, int transactionType, Integer amount, Date created_at){
//        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.transactionType = TransactionTypes.getTransactionTypeName(transactionType);
        this.amount = amount;
        this.created_at = created_at;
    }

    public static TransactionLogsDto create(TransactionLogs transactionLogs){
        TransactionLogsDto transactionLogsDto = new TransactionLogsDto();
        transactionLogsDto.setMemberId(transactionLogs.getMemberId());
        transactionLogsDto.setMemberName(transactionLogs.getMemberName());
        transactionLogsDto.setTransactionType(transactionLogs.getTransactionType());
        transactionLogsDto.setAmount(transactionLogs.getAmount());
        transactionLogsDto.setCreated_at(transactionLogs.getCreated_at());
        transactionLogsDto.setUpdated_at(transactionLogs.getUpdated_at());
        return transactionLogsDto;
    }
}
