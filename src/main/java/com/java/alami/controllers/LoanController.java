package com.java.alami.controllers;

import com.java.alami.dto.DepositDto;
import com.java.alami.dto.LoanDto;
import com.java.alami.requests.DepositRequest;
import com.java.alami.requests.LoanRequest;
import com.java.alami.services.DepositService;
import com.java.alami.services.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/loan")
@Slf4j
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @GetMapping
    public Page<LoanDto> getAllLoan(Pageable pageable) {
        return loanService.getAllLoan(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDto createLoan(@RequestBody @Valid LoanRequest loanRequest){
        return loanService.createLoan(loanRequest);
    }
}
