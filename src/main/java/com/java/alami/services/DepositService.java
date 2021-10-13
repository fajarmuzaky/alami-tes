package com.java.alami.services;

import com.java.alami.dao.DepositRepository;
import com.java.alami.dao.MemberRepository;
import com.java.alami.dto.DepositDto;
import com.java.alami.entity.Deposit;
import com.java.alami.entity.Member;
import com.java.alami.requests.DepositRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DepositService {
    private final DepositRepository depositRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, MemberRepository memberRepository){
        this.depositRepository = depositRepository;
        this.memberRepository = memberRepository;
    }

    public Page<DepositDto> getAllDeposit(Pageable pageable) {
        Page<Deposit> depositPage = depositRepository.findAll(pageable);
        return depositPage.map(DepositDto::create);
    }

    public Deposit toDeposit(String email, Integer amount, Date created_at) {
        Deposit deposit = new Deposit();
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new RuntimeException("Cannot find member with email " + email);
        }
        Deposit checkDeposit = depositRepository.findByIdMember(member);
        deposit.setMember(member);
        deposit.setCreated_at(created_at);
        if( checkDeposit != null){
            Integer newBalance = checkDeposit.getBalance() + amount;
            deposit.setId(checkDeposit.getId());
            deposit.setBalance(newBalance);
            deposit.setCreated_at(checkDeposit.getCreated_at());
            deposit.setUpdated_at(created_at);
            return depositRepository.save(deposit);
        }
        deposit.setBalance(amount);
        return depositRepository.save(deposit);
    }

    public DepositDto createDeposit(DepositRequest depositRequest){
        Deposit deposit = toDeposit(depositRequest.getEmail(), depositRequest.getAmount(), depositRequest.getCreated_at());
        return DepositDto.create(deposit);
    }
}
