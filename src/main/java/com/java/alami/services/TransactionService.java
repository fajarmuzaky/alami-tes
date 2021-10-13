package com.java.alami.services;

import com.java.alami.constants.TransactionTypes;
import com.java.alami.dao.MemberRepository;
import com.java.alami.dao.TransactionRepository;
import com.java.alami.dto.TransactionDto;
import com.java.alami.dto.TransactionLogsDto;
import com.java.alami.entity.Member;
import com.java.alami.entity.Transaction;
import com.java.alami.entity.TransactionLogs;
import com.java.alami.filters.TransactionFilter;
import com.java.alami.publisher.TransactionPublisher;
import com.java.alami.requests.TransactionRequest;
import com.java.alami.spesifications.TransactionSpecification;
import com.java.alami.validation.DateValidator;
import com.java.alami.validation.DateValidatorImpl;
import com.java.alami.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final LoanService loanService;
    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final RepaymentService repaymentService;

    @Autowired
    public TransactionService(LoanService loanService,
                              MemberRepository memberRepository,
                              TransactionRepository transactionRepository,
                              DepositService depositService,
                              WithdrawService withdrawService,
                              RepaymentService repaymentService,
                              MongoService mongoService){
        this.loanService = loanService;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.repaymentService = repaymentService;
    }

    @Autowired
    private TransactionPublisher transactionPublisher;

    public Pagination<TransactionDto> getAllTransaction(TransactionFilter transactionFilter, Pageable pageable, Integer draw) {
        TransactionSpecification transactionSpecification = new TransactionSpecification(transactionFilter);
        return new Pagination<>(draw,
                Math.toIntExact(transactionRepository.count()),
                transactionRepository.findAll(transactionSpecification).size(),
                transactionRepository.findAll(transactionSpecification, pageable).map(TransactionDto::create).getContent());
    }

    public Transaction toTransaction(TransactionRequest transactionRequest, Date transactionDate, Transaction transaction) {
        Member member = memberRepository.findByEmail(transactionRequest.getEmail());
        if (member == null){
            throw new RuntimeException("Cannot find member with email " + transactionRequest.getEmail());
        }
        int type = TransactionTypes.getTransactionTypeId(transactionRequest.getType());
        if (type == 0) {
            throw new RuntimeException("Cannot log this transaction cause type is not defined");
        }
        transaction.setMember(member);
        transaction.setTransactionType(type);
        transaction.setCreated_at(transactionDate);
        transaction.setAmount(transactionRequest.getAmount());

        TransactionLogsDto transactionLogsDto = toTransactionLogs(transaction);
        transactionPublisher.publish(transactionLogsDto);
        return transactionRepository.save(transaction);
    }

    public TransactionLogsDto toTransactionLogs(Transaction transaction) {
        TransactionLogsDto transactionLogsDto = new TransactionLogsDto();
        transactionLogsDto.setMemberId(transaction.getMember().getId());
        transactionLogsDto.setTransactionType(TransactionTypes.getTransactionTypeName(transaction.getTransactionType()));
        transactionLogsDto.setMemberName(transaction.getMember().getName());
        transactionLogsDto.setAmount(transaction.getAmount());
        transactionLogsDto.setCreated_at(transaction.getCreated_at());
        return transactionLogsDto;
    }

    public TransactionDto create(TransactionRequest transactionRequest) throws ParseException {
        DateValidator validator = new DateValidatorImpl("dd-MM-yyyy");
        if(!validator.isValid(transactionRequest.getCreated_at())) {
            throw new RuntimeException("error cause create date format is not valid, please use format dd-MM-yyyy");
        }
        Date transactionDate = validator.formatter(transactionRequest.getCreated_at());
        Date date = new Date();
        if (transactionDate.after(date)) {
            throw new RuntimeException("error cause date not valid");
        }

        Transaction transaction = toTransaction(transactionRequest, transactionDate ,new Transaction());
        if (transaction.getTransactionType() == TransactionTypes.DEPOSIT) {
            depositService.toDeposit(transactionRequest.getEmail(), transactionRequest.getAmount(), transactionDate);
        } else if (transaction.getTransactionType() == TransactionTypes.lOAN) {
            loanService.toLoan(transactionRequest.getEmail(), transactionRequest.getAmount(), transactionDate);
        } else if (transaction.getTransactionType() == TransactionTypes.WITHDRAW) {
            withdrawService.toWithdraw(transactionRequest.getEmail(), transactionRequest.getAmount());
        } else if (transaction.getTransactionType() == TransactionTypes.REPAYMENT) {
            repaymentService.toRepayment(transactionRequest.getEmail(), transactionRequest.getAmount(), transactionDate);
        }
        return TransactionDto.create(transaction);
    }

    public List<TransactionDto> getTransactionByMember(Long id_member){
        Optional<Member> resultMember = memberRepository.findById(id_member);
        if(! resultMember.isPresent()) {
            throw new RuntimeException("cannot found user with id " + id_member);
        }
        Member member = resultMember.get();
        return transactionRepository.findByMember(member);
    }
}
