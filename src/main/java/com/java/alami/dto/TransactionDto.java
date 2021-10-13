package com.java.alami.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.constants.TransactionTypes;
import com.java.alami.entity.Member;
import com.java.alami.entity.Transaction;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Long id;
    private MemberDto member;
    private String transactionType;
    private Integer amount;

    @JsonFormat(pattern="dd-MMMM-yyyy", timezone="Asia/Jakarta")
    private Date created_at;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date updated_at;

    public TransactionDto() {}

    public TransactionDto(Long id, Member member,Integer transactionType, Integer amount, Date created_at){
        this.id = id;
        this.member = MemberDto.create(member);
        this.transactionType = TransactionTypes.getTransactionTypeName(transactionType);
        this.amount = amount;
        this.created_at = created_at;
    }

    public static TransactionDto create(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setMember(MemberDto.create(transaction.getMember()));
        transactionDto.setTransactionType(TransactionTypes.getTransactionTypeName(transaction.getTransactionType()));
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setCreated_at(transaction.getCreated_at());
        transactionDto.setUpdated_at(transaction.getUpdated_at());
        return transactionDto;
    }
}
