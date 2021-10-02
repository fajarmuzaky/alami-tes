package com.java.alami.controllers;

import com.java.alami.dto.MemberDto;
import com.java.alami.dto.TransactionDto;
import com.java.alami.entity.Member;
import com.java.alami.requests.MemberFilterRequest;
import com.java.alami.requests.MemberRequest;
import com.java.alami.services.MemberService;
import com.java.alami.vo.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public Pagination<MemberDto> findAllMembers(@RequestBody @Valid MemberFilterRequest memberFilterRequest, Integer draw){
        PageRequest pageRequest = PageRequest.of(memberFilterRequest.getStart()/ memberFilterRequest.getLength(), memberFilterRequest.getLength(), Sort.by("id").descending());
        return memberService.findAll(pageRequest, draw);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto createMember(@RequestBody @Valid MemberRequest memberRequest){
        return memberService.createMember(memberRequest);
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id){
        return memberService.getMemberById(id);
    }
}
