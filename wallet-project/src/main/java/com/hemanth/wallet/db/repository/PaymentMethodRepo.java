package com.hemanth.wallet.db.repository;


import com.hemanth.wallet.db.entity.WalletPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepo extends JpaRepository<WalletPaymentMethod , Integer> {

}
