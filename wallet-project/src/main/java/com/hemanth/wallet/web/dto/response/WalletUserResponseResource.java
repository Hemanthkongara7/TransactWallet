package com.hemanth.wallet.web.dto.response;

import com.hemanth.wallet.db.entity.WalletTransaction;
import com.hemanth.wallet.service.dto.WalletUserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class WalletUserResponseResource {

    private Long id;
    private String name;
    private Integer age;
    private String pnCountryCode;
    private String phoneNumber;
    private String email;
    private WalletUserStatus status;
    private List<WalletTransaction> transactions;

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

}
