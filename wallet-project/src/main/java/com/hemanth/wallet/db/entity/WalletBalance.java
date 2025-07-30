package com.hemanth.wallet.db.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hemanth.wallet.service.dto.WalletTransactionPaymentMethod;
import com.hemanth.wallet.service.dto.WalletTransactionStatus;
import com.hemanth.wallet.service.dto.WalletTransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
@Table (name = "tbl_wallet")
public class WalletBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="c_id",nullable = false,unique = true)
    private Long id ;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_user_id", referencedColumnName = "id",updatable = false)
    @JsonBackReference
    private WalletUser walletUser;
    @Column(name ="c_outstanding_balance" , nullable = false , precision = 18, scale = 2)
    private BigDecimal outStandingBalance ;
    @Column(name ="c_uncleared_amount" , nullable = false , precision = 18, scale = 2)
    private BigDecimal unclearedAmount;
    @Column(name ="c_comments")
    private String comments ;

    public WalletBalance(Long id,  BigDecimal outStandingBalance,BigDecimal unclearedAmount, String comments ,  WalletUser walletUser) {
        this.id = id;
        this.outStandingBalance = outStandingBalance;
        this.unclearedAmount = unclearedAmount;
        this.comments = comments;
        this.walletUser = walletUser;
    }
}
