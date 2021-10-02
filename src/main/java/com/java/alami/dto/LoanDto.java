package com.java.alami.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.entity.Deposit;
import com.java.alami.entity.Loan;
import lombok.Data;

import java.util.Date;

@Data
public class LoanDto {
    private Long id;
    private MemberDto member;
    private Integer amount;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date created_at;

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date updated_at;

    public LoanDto() {}

    public static LoanDto create(Loan loan){
        LoanDto depositDto = new LoanDto();
        depositDto.setId(loan.getId());
        depositDto.setMember(MemberDto.create(loan.getMember()));
        depositDto.setAmount(loan.getAmount());
        depositDto.setCreated_at(loan.getCreated_at());
        depositDto.setUpdated_at(loan.getUpdated_at());
        return depositDto;
    }
}
