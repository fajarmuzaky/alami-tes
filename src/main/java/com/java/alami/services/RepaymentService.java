package com.java.alami.services;

import com.java.alami.dao.LoanRepository;
import com.java.alami.dao.MemberRepository;
import com.java.alami.entity.Loan;
import com.java.alami.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RepaymentService {
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final DepositService depositService;

    @Autowired
    public RepaymentService(LoanRepository loanRepository, MemberRepository memberRepository, DepositService depositService){
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.depositService = depositService;
    }

    public Loan toRepayment(String email,Integer amount, Date created_at) {
        Loan loan = new Loan();
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new RuntimeException("Cannot find member with email " + email);
        }
        Loan loanExist = loanRepository.findByMember(member);
        int newAmount = 0;
        Date date = new Date();
        loan.setMember(member);
        if( loanExist != null){
            newAmount = loanExist.getAmount() - amount;
        } else if(amount > loanExist.getAmount()){
            newAmount = 0;
            int remainingAmount = amount - loanExist.getAmount();
            depositService.toDeposit(email, remainingAmount, created_at);
        } else {
            throw new RuntimeException("can't repay money because the member doesn't have a loan balance");
        }
        loan.setId(loanExist.getId());
        loan.setAmount(newAmount);
        loan.setCreated_at(loanExist.getCreated_at());
        loan.setUpdated_at(created_at);
        return loanRepository.save(loan);
    }
}
