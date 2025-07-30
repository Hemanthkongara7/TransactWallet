package com.hemanth.wallet.service.dto;

import com.hemanth.wallet.db.entity.WalletTransaction;

import java.util.List;

public class WalletUserResponseDto {

    private Long id ;
    private String name;
    private Integer age;
    private String pnCountryCode;
    private String phoneNumber;
    private String email;
    private WalletUserStatus status;
    private List<WalletTransaction> transactions;
    private String comments;
    private String agent;
    private Long outStandingBalance;
    private WalletUserRole roles ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPnCountryCode() {
        return pnCountryCode;
    }

    public void setPnCountryCode(String pnCountryCode) {
        this.pnCountryCode = pnCountryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WalletUserStatus getStatus() {
        return status;
    }

    public void setStatus(WalletUserStatus status) {
        this.status = status;
    }

    public List<WalletTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WalletTransaction> transactions) {
        this.transactions = transactions;
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

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public Long getOutStandingBalance() {
        return outStandingBalance;
    }

    public void setOutStandingBalance(Long outStandingBalance) {
        this.outStandingBalance = outStandingBalance;
    }

    public WalletUserRole getRoles() {
        return roles;
    }

    public void setRoles(WalletUserRole roles) {
        this.roles = roles;
    }
}
