package com.hemanth.wallet.service.dto;

import com.hemanth.wallet.db.entity.WalletTransaction;
import com.hemanth.wallet.db.entity.WalletUser;

public class WalletTransactionResponseDto {

    private Long id ;
    private Long amount ;
    private WalletTransactionType type;
    private WalletTransactionStatus status;
    private String comments ;
    private String agent ;
    private WalletTransactionPaymentMethod paymentMethod;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public WalletTransactionType getType() {
        return type;
    }

    public void setType(WalletTransactionType type) {
        this.type = type;
    }

    public WalletTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(WalletTransactionStatus status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public WalletTransactionPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(WalletTransactionPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
