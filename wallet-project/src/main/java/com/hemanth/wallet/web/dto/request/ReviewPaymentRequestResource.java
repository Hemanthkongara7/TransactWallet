package com.hemanth.wallet.web.dto.request;

import com.hemanth.wallet.service.dto.WalletTransactionPaymentMethod;
import com.hemanth.wallet.service.dto.WalletTransactionStatus;
import com.hemanth.wallet.service.dto.WalletTransactionType;

public class ReviewPaymentRequestResource {
    private WalletTransactionStatus status;

    public WalletTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(WalletTransactionStatus status) {
        this.status = status;
    }
}
