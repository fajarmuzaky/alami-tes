package com.java.alami.dao;

import com.java.alami.entity.Deposit;
import com.java.alami.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

    @Query("SELECT a FROM Deposit a WHERE a.member = :id_member")
    Deposit findByIdMember(@Param("id_member") Member id_member);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Deposit a SET a.balance = :balance WHERE a.member = :id_member")
    void updateBalance(@Param("balance") Integer balance, @Param("id_member") Member id_member);
}
