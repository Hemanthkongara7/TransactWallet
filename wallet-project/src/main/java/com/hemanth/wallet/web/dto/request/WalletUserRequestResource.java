package com.hemanth.wallet.web.dto.request;

import com.hemanth.wallet.db.entity.WalletTransaction;

import java.util.List;

public class WalletUserRequestResource {
    private String name;
    private Integer age;
    private String pnCountryCode;
    private String phoneNumber;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String password;

}
