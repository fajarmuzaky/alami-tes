package com.java.alami.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.alami.dto.TransactionDto;
import com.java.alami.filters.TransactionFilter;
import com.java.alami.requests.TransactionFilterRequest;
import com.java.alami.requests.TransactionRequest;
import com.java.alami.services.TransactionService;
import com.java.alami.vo.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public Pagination<TransactionDto> getAllTransaction(@RequestBody @Valid TransactionFilterRequest transactionFilterRequest, Integer draw) {
        TransactionFilter transactionFilter = new TransactionFilter();
        transactionFilter.setStartDate(transactionFilterRequest.getStart_of_date());
        transactionFilter.setEndDate(transactionFilterRequest.getEnd_of_date());
        PageRequest pageRequest = PageRequest.of(transactionFilterRequest.getStart()/ transactionFilterRequest.getLength(), transactionFilterRequest.getLength(), Sort.by("id").descending());
        return transactionService.getAllTransaction(transactionFilter, pageRequest, draw);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto create(@RequestBody @Valid TransactionRequest transactionRequest){
        return transactionService.create(transactionRequest);
    }

    @GetMapping("/{id_member}")
    public List<TransactionDto> getTransactionByMember(@PathVariable Long id_member){
        return transactionService.getTransactionByMember(id_member);
    }
}
