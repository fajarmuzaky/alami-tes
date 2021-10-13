package com.java.alami.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TransactionRequest {

    @NotEmpty(message = "{error.transaction.validation.email.empty}")
    private String email;

    @NotEmpty(message = "{error.transaction.validation.transactionType.empty}")
    private String type;

    @NotEmpty(message = "{error.transaction.validation.amount.empty}")
    private Integer amount;

    @NotEmpty(message = "{error.transaction.validation.date.empty}")
    private String created_at;

}
