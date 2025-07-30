package com.hemanth.wallet.web.dto.response;

import com.hemanth.wallet.db.entity.WalletTransaction;
import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.service.dto.WalletTransactionPaymentMethod;
import com.hemanth.wallet.service.dto.WalletTransactionStatus;
import com.hemanth.wallet.service.dto.WalletTransactionType;

public class WalletTransactionResponseResource {

    private Long id ;
    private Long amount ;
    private WalletTransactionType type;
    private WalletTransactionStatus status;
    private String comments ;
    private String agent ;
    private WalletTransactionPaymentMethod paymentMethod;

    public WalletTransactionResponseResource(Long id, WalletUser walletUser, Long amount, WalletTransactionType type, WalletTransactionStatus status, String comments, String agent, WalletTransactionPaymentMethod paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.comments = comments;
        this.agent = agent;
        this.paymentMethod = paymentMethod;
    }

    public WalletTransactionResponseResource() {
    }

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
