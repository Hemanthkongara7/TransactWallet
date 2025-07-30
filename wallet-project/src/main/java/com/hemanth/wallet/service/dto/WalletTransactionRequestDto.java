package com.hemanth.wallet.service.dto;

public class WalletTransactionRequestDto {
    private Long userId;
    private Long amount;
    private WalletTransactionType type;
    private String comments;
    private String agent;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private WalletTransactionPaymentMethod paymentMethod;
    private String phoneNumber;


}
