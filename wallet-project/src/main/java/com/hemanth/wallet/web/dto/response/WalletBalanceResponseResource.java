package com.hemanth.wallet.web.dto.response;

import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.service.dto.WalletTransactionPaymentMethod;
import com.hemanth.wallet.service.dto.WalletTransactionStatus;
import com.hemanth.wallet.service.dto.WalletTransactionType;

public class WalletBalanceResponseResource {

    private Long id ;

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

    public WalletTransactionPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(WalletTransactionPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private Long amount ;
    private WalletTransactionType type;
    private WalletTransactionStatus status;
    private WalletTransactionPaymentMethod paymentMethod;

}
