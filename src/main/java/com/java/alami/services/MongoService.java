package com.java.alami.services;

import com.java.alami.constants.TransactionTypes;
import com.java.alami.dao.Mongo.LogMemberRepository;
import com.java.alami.dao.Mongo.LogTransactionRepository;
import com.java.alami.dto.TransactionLogsDto;
import com.java.alami.entity.Member;
import com.java.alami.entity.TransactionLogs;
import com.java.alami.filters.TransactionFilter;
import com.java.alami.requests.MemberRequest;
import com.java.alami.requests.TransactionRequest;
import com.java.alami.spesifications.TransactionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MongoService {
    private final LogMemberRepository logMemberRepository;
    private final LogTransactionRepository logTransactionRepository;
    //private final KafkaProducerService kafkaProducerService;

    @Autowired
    public MongoService(LogMemberRepository logMemberRepository,
                        LogTransactionRepository logTransactionRepository){
        this.logMemberRepository = logMemberRepository;
        this.logTransactionRepository  = logTransactionRepository;
        //this.kafkaProducerService = kafkaProducerService;

    }

    public Member logMembers(MemberRequest memberRequest, Long id, Member member){
        member.setId(id);
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        member.setDate_of_birth(memberRequest.getDay_of_birth());
        member.setAddress(memberRequest.getAddress());
        return logMemberRepository.save(member);
    }

    public TransactionLogs create(TransactionLogsDto transactionLogsDto){
        TransactionLogs transactionLogs = TransactionLogs.mapperCreateLogs(transactionLogsDto);
        return logTransactionRepository.save(transactionLogs);
    }

    public Page<TransactionLogsDto> getTransactionLogs(TransactionFilter transactionFilter, Pageable pageable, Integer draw) {
        TransactionSpecification transactionSpecification = new TransactionSpecification(transactionFilter);
        List<TransactionLogs> transactionList = logTransactionRepository.findAll();
        Page<TransactionLogs> transactionPage = new PageImpl<>(transactionList, pageable, transactionList.size());
        return transactionPage.map(TransactionLogsDto::create);
    }

    public Page<TransactionLogsDto> getTransactionLogsByMember(Long id_member, Pageable pageable){
        Optional<Member> resultMember = logMemberRepository.findById(id_member);
        if(! resultMember.isPresent()) {
            throw new RuntimeException("cannot found user with id " + id_member);
        }
        Member member = resultMember.get();
        List<TransactionLogs> transactionList = logTransactionRepository.findByMemberId(id_member);
        Page<TransactionLogs> transactionPage = new PageImpl<>(transactionList, pageable, transactionList.size());
        return transactionPage.map(TransactionLogsDto::create);
    }

}
