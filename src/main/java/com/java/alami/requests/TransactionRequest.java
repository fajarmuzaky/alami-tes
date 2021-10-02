package com.java.alami.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class TransactionRequest {

    @NotEmpty(message = "{error.transaction.validation.email.empty}")
    private String email;

    @NotEmpty(message = "{error.transaction.validation.transactionType.empty}")
    private String type;

    @NotEmpty(message = "{error.transaction.validation.amount.empty}")
    private Integer amount;

    @NotEmpty(message = "{error.transaction.validation.amount.empty}")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date created_at;

//    public static TransactionRequest create(TransactionDto transactionDto){
//        TransactionRequest transactionRequest = new TransactionRequest();
//        transactionRequest.setEmail(transactionDto.getMember().getEmail());
//        transactionRequest.setType(transactionDto.getTransactionType());
//        transactionRequest.setAmount(transactionDto.getAmount());
//        return transactionRequest;
//    }
}
