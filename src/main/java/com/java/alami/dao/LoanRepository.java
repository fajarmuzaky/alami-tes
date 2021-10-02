package com.java.alami.dao;

import com.java.alami.entity.Deposit;
import com.java.alami.entity.Loan;
import com.java.alami.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT a FROM Loan a WHERE a.member = :id_member")
    Loan findByMember(@Param("id_member") Member id_member);
}
