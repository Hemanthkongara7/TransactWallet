package com.hemanth.wallet.web.Security;

import com.hemanth.wallet.service.dto.WalletUserRole;

import java.lang.annotation.*;
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorized {
    WalletUserRole[] roles() default {};
}
