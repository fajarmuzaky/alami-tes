package com.java.alami.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.entity.Deposit;
import lombok.Data;

import java.util.Date;

@Data
public class DepositDto {
    private Long id;
    private MemberDto member;
    private Integer balance;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date created_at;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date updated_at;

    public DepositDto() {}

    public static DepositDto create(Deposit deposit){
        DepositDto depositDto = new DepositDto();
        depositDto.setId(deposit.getId());
        depositDto.setMember(MemberDto.create(deposit.getMember()));
        depositDto.setBalance(deposit.getBalance());
        depositDto.setCreated_at(deposit.getCreated_at());
        depositDto.setUpdated_at(deposit.getUpdated_at());
        return depositDto;
    }
}
