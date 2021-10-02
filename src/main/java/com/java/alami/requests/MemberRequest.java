package com.java.alami.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.dto.MemberDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class MemberRequest {

    @NotEmpty(message = "{error.members.validation.name.empty}")
    private String name;

    @NotEmpty(message = "{error.members.validation.email.empty}")
    private String email;

    @NotEmpty(message = "{error.members.validation.dayofbirth.empty}")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date day_of_birth;

    @NotEmpty(message = "{error.user.validation.address.empty}")
    private String address;

    public static MemberRequest create(MemberDto memberDto){
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName(memberDto.getName());
        memberRequest.setDay_of_birth(memberDto.getDay_of_birth());
        memberRequest.setAddress(memberDto.getAddress());
        return memberRequest;
    }
}
