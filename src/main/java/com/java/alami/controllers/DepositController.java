package com.java.alami.controllers;

import com.java.alami.dto.DepositDto;
import com.java.alami.requests.DepositRequest;
import com.java.alami.services.DepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/deposit")
@Slf4j
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService){
        this.depositService = depositService;
    }

    @GetMapping
    public Page<DepositDto> getAllDeposit(Pageable pageable) {
        return depositService.getAllDeposit(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepositDto createDeposit(@RequestBody @Valid DepositRequest depositRequest){
        return depositService.createDeposit(depositRequest);
    }
}
