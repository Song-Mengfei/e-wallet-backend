package com.ewallet.springbootewallet.domain;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "transaction")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tid;
    private long fromAccountId;
    private long toAccountId;
    private String type;
    private BigDecimal amount;
    private String remark;
    private String status;
    private String time;
}


