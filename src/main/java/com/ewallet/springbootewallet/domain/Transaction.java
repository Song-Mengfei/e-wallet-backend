package com.ewallet.springbootewallet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    private Double amount;
    private String remark;
    private String status;
    private String time;

    public long getTid() {
        return tid;
    }

    public Transaction() {
    }

    private Transaction(long fromAccountId, long toAccountId, String type, Double amount, String remark, String status, String time) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.amount = amount;
        this.remark = remark;
        this.status = status;
        this.time = time;
    }

    public static Transaction build(long fromAccountId, long toAccountId, String type, Double amount, String remark, String status, String time) {
        return new Transaction(fromAccountId, toAccountId, type, amount, remark, status, time);
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


