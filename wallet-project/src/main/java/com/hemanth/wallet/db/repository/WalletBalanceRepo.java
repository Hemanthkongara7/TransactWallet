package com.hemanth.wallet.db.repository;

import com.hemanth.wallet.db.entity.WalletBalance;
import com.hemanth.wallet.db.entity.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletBalanceRepo extends JpaRepository<WalletBalance, Long> {
//   WalletBalance findByWalletUser(WalletUser walletUser);

    WalletBalance findByWalletUserId(Long id);


}
