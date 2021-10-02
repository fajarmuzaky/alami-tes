package com.java.alami.services;

import com.java.alami.dao.LoanRepository;
import com.java.alami.dao.MemberRepository;
import com.java.alami.dto.LoanDto;
import com.java.alami.entity.Loan;
import com.java.alami.entity.Member;
import com.java.alami.requests.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, MemberRepository memberRepository){
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
    }

    public Page<LoanDto> getAllLoan(Pageable pageable) {
        Page<Loan> loanPage = loanRepository.findAll(pageable);
        return loanPage.map(LoanDto::create);
    }

    public Loan toLoan(String email, Integer amount) {
        Loan loan = new Loan();
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new RuntimeException("Cannot find member with email" + email);
        }
        Loan checkLoan = loanRepository.findByMember(member);
        Date date = new Date();
        loan.setMember(member);
        loan.setCreated_at(date);
        if( checkLoan != null){
            Integer newAmount = checkLoan.getAmount() + amount;
            loan.setId(checkLoan.getId());
            loan.setAmount(newAmount);
            loan.setUpdated_at(date);
            return loanRepository.save(loan);
        }
        loan.setAmount(amount);
        return loanRepository.save(loan);
    }

    public LoanDto createLoan(LoanRequest loanRequest){
        Loan loan = toLoan(loanRequest.getEmail(), loanRequest.getAmount());
        return LoanDto.create(loan);
    }
}
