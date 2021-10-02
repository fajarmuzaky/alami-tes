package com.java.alami.services;

import com.java.alami.dao.DepositRepository;
import com.java.alami.dao.MemberRepository;
import com.java.alami.dto.DepositDto;
import com.java.alami.entity.Deposit;
import com.java.alami.entity.Member;
import com.java.alami.requests.DepositRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WithdrawService {
    private final DepositRepository depositRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public WithdrawService(DepositRepository depositRepository, MemberRepository memberRepository){
        this.depositRepository = depositRepository;
        this.memberRepository = memberRepository;
    }

    public Deposit toWithdraw(String email, Integer amount) {
        Deposit deposit = new Deposit();
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new RuntimeException("Cannot find member with email" + email);
        }
        Deposit checkDeposit = depositRepository.findByIdMember(member);
        Date date = new Date();
        deposit.setMember(member);
        if( checkDeposit != null && checkDeposit.getBalance() > amount){
            Integer newBalance = checkDeposit.getBalance() - amount;
            deposit.setId(checkDeposit.getId());
            deposit.setBalance(newBalance);
            deposit.setCreated_at(checkDeposit.getCreated_at());
            deposit.setUpdated_at(date);
            return depositRepository.save(deposit);
        } else {
            throw new RuntimeException("can't withdraw money because the member doesn't have a deposit balance or the withdrawal amount is greater than the deposit balance");
        }
    }

}
