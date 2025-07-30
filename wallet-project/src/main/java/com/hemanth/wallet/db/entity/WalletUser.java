package com.hemanth.wallet.db.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hemanth.wallet.service.dto.WalletUserRole;
import com.hemanth.wallet.service.dto.WalletUserStatus;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Transactional
@NoArgsConstructor
@Data
@Table(name = "tbl_user")
public class WalletUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="id",nullable = false,unique = true)
    private Long id ;
    @Column (name ="c_name",nullable = false,length = 100)
    private String name;
    @Column(name ="c_age")
     private Integer age;
    @Column(name ="c_pn_country_code",nullable = false,length = 6)
    private String pnCountryCode;
    @Column(name ="c_phone_number",nullable = false,length = 100,unique = true)
    private String phoneNumber;
    @Column(name ="c_email",nullable = false,length = 100,unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name ="c_status", nullable = false)
    private WalletUserStatus status;
    @Column(name ="c_comments",length = 200)
    private String comments;
    @Column(name ="c_agent",length = 50)
    private String agent;
    @OneToMany(mappedBy = "walletUser", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<WalletTransaction> transactions;
    @NotNull()
    @Convert(converter = HashConverter.class)
    @Column(name="c_password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name ="c_role",nullable = false)
    private WalletUserRole role;
    @OneToOne(mappedBy = "walletUser", fetch  = FetchType.LAZY)
    @JsonManagedReference
    public WalletBalance walletBalance;

    public WalletUser(Long id, String name, Integer age, String pnCountryCode, String phoneNumber,
                      String email,WalletUserStatus status , String comments, String agent , String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.pnCountryCode = pnCountryCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.status = status;
        this.comments = comments;
        this.agent = agent;
        this.password=password;


    }






}
