package com.java.alami.requests;

import com.java.alami.dto.DepositDto;
import com.java.alami.dto.LoanDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoanRequest {

    @NotEmpty(message = "{error.loan.validation.email.empty}")
    private String email;

    @NotEmpty(message = "{error.loan.validation.amount.empty}")
    private Integer amount;

    public static LoanRequest create(LoanDto loanDto){
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setEmail(loanDto.getMember().getEmail());
        loanRequest.setAmount(loanDto.getAmount());
        return loanRequest;
    }
}
