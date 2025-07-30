package com.hemanth.wallet.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemanth.wallet.service.dto.WalletTransactionPaymentMethod;
import com.hemanth.wallet.service.dto.WalletTransactionStatus;
import com.hemanth.wallet.service.dto.WalletTransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.usertype.UserType;

import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table (name = "tbl_transaction")
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="c_id",nullable = false,unique = true)
    private Long id ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="c_user_id", referencedColumnName = "id")
    @JsonBackReference
    private WalletUser walletUser ;
    @Column(name ="c_amount")
    private Long amount ;
    @Enumerated(EnumType.STRING)
    @Column(name ="c_type")
    private WalletTransactionType type;
    @Enumerated(EnumType.STRING)
    @Column(name ="c_status")
    private WalletTransactionStatus status;
    @Column(name ="c_comments",length = 200)
    private String comments ;
    @Column(name ="c_agent",length = 50)
    private String agent ;
    @Enumerated(EnumType.STRING)
    @Column(name ="c_payment_method",nullable = false)
    private WalletTransactionPaymentMethod paymentMethod;

    @Column(name = "c_debitor_transaction_id")
    private Long debitorTransactionId ;


    public WalletTransaction(Long id, WalletUser walletUser, Long amount, WalletTransactionType type,  WalletTransactionStatus status,
                             String comments,String agent, WalletTransactionPaymentMethod paymentMethod , Long debitorTransactionId) {
        this.id = id;
        this.walletUser = walletUser;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.comments = comments;
        this.agent = agent;
        this.paymentMethod = paymentMethod;
        this.debitorTransactionId = debitorTransactionId;
    }
}
