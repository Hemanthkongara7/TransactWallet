package com.hemanth.wallet.web.Security;

import com.hemanth.wallet.service.dto.WalletUserRole;
import io.jsonwebtoken.Claims;

public class AuthenticationClaims {

    private Long id;
    private String email;
    private String name;
    private WalletUserRole roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WalletUserRole getRoles() {
        return roles;
    }

    public void setRoles(WalletUserRole roles) {
        this.roles = roles;
    }

    public AuthenticationClaims(Long id, String email, String name , WalletUserRole roles) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }

    public static AuthenticationClaims fromClaims(Claims claims) {
        return new AuthenticationClaims(
                Long.parseLong((String) claims.get("id")),
                (String) claims.get("email"),
                (String) claims.get("name"),
                (WalletUserRole) claims.get("roles")
        );
    }
}

