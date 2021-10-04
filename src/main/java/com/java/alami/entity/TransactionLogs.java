package com.java.alami.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Document(collection = "transaction_logs")
@Data
public class TransactionLogs {
    @Id
    private Long id;

    @Column(name = "id_member")
    private Long memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "transaction_type")
    private int transactionType;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

}
