package com.hemanth.wallet.db.repository;


import com.hemanth.wallet.db.entity.WalletTransaction;
import com.hemanth.wallet.db.entity.WalletUser;
import com.hemanth.wallet.service.dto.WalletTransactionResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<WalletTransaction, Long> {

        List<WalletTransaction> findByWalletUserPhoneNumber(String phoneNumber);

        Optional<WalletTransaction> findByIdAndWalletUserId(Long id, Long userId);

        List<WalletTransaction> findByWalletUserId(Long userId);
}
