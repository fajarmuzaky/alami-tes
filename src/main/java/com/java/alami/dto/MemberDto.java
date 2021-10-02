package com.java.alami.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.entity.Member;
import lombok.Data;

import java.util.Date;

@Data
public class MemberDto {
    private Long id;
    private String name;
    private String email;


    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date day_of_birth;

    private String address;

    public MemberDto() {}

    public MemberDto(Long id, String name, String email, Date day_of_birth, String address){
        this.id = id;
        this.name = name;
        this.email = email;
        this.day_of_birth = day_of_birth;
        this.address = address;
    }

    public static MemberDto create(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        memberDto.setDay_of_birth(member.getDate_of_birth());
        memberDto.setAddress(member.getAddress());
        return memberDto;
    }
}
