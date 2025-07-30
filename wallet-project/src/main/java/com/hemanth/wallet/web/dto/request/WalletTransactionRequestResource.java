package com.hemanth.wallet.web.dto.request;

import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.service.dto.WalletTransactionPaymentMethod;
import com.hemanth.wallet.service.dto.WalletTransactionRequestDto;
import com.hemanth.wallet.service.dto.WalletTransactionStatus;
import com.hemanth.wallet.service.dto.WalletTransactionType;

public class WalletTransactionRequestResource extends WalletTransactionRequestDto {


    private Long userId;
    private Long amount;
    private WalletTransactionType type;
    private String comments;
    private String agent;
    private WalletTransactionPaymentMethod paymentMethod;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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