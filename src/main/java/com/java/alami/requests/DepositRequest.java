package com.java.alami.requests;

import com.java.alami.dto.DepositDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DepositRequest {

    @NotEmpty(message = "{error.deposit.validation.email.empty}")
    private String email;

    @NotEmpty(message = "{error.deposit.validation.amount.empty}")
    private Integer amount;

    public static DepositRequest create(DepositDto depositDto){
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setEmail(depositDto.getMember().getEmail());
        depositRequest.setAmount(depositDto.getBalance());
        return depositRequest;
    }
}
