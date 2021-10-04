package com.java.alami.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.dto.DepositDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class DepositRequest {

    @NotEmpty(message = "{error.deposit.validation.email.empty}")
    private String email;

    @NotEmpty(message = "{error.deposit.validation.amount.empty}")
    private Integer amount;

    @NotEmpty(message = "{error.deposit.validation.created_at.empty}")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date created_at;

    public static DepositRequest create(DepositDto depositDto){
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setEmail(depositDto.getMember().getEmail());
        depositRequest.setAmount(depositDto.getBalance());
        depositRequest.setCreated_at(depositDto.getCreated_at());
        return depositRequest;
    }
}
