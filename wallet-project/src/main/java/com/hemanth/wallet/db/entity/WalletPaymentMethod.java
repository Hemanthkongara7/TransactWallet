package com.hemanth.wallet.db.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_payment_method")
public class WalletPaymentMethod {
    @Column(name ="c_id",unique = true)
    @Id
    private Long id ;
    @Column(name ="c_method",nullable = false)
    private String method;


    public WalletPaymentMethod(Long id, String method) {
        this.id = id;
        this.method = method;
    }
}
