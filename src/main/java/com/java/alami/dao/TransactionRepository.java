package com.java.alami.dao;

import com.java.alami.dto.TransactionDto;
import com.java.alami.entity.Member;
import com.java.alami.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    @Query("SELECT new com.java.alami.dto.TransactionDto(a.id, a.member, a.transactionType, a.amount, a.created_at)"
            + "FROM Transaction a WHERE a.member = :member")
    List<TransactionDto> findByMember(@Param("member") Member member);
}
