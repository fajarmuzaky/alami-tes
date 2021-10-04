package com.java.alami.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.dto.DepositDto;
import com.java.alami.dto.LoanDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class LoanRequest {

    @NotEmpty(message = "{error.loan.validation.email.empty}")
    private String email;

    @NotEmpty(message = "{error.loan.validation.amount.empty}")
    private Integer amount;

    @NotEmpty(message = "{error.loan.validation.created_at.empty}")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date created_at;

    public static LoanRequest create(LoanDto loanDto){
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setEmail(loanDto.getMember().getEmail());
        loanRequest.setAmount(loanDto.getAmount());
        loanRequest.setCreated_at(loanDto.getCreated_at());
        return loanRequest;
    }
}
