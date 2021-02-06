package com.atm_rest_springboot.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atm_rest_springboot.entity.Account;

@Transactional
public interface AccountRepo extends CrudRepository<Account, Integer>

{
	Optional<Account> findByAccountNumber(int customerNumber);

	@Query(value = "SELECT BALANCE FROM USER_ACCOUNT WHERE ACCOUNT_NUMBER =:customerNumber ", nativeQuery = true)
	public double cekSaldoAwal(@Param("customerNumber") int customerAccount);

	@Modifying
	@Query(value = "UPDATE USER_ACCOUNT SET BALANCE =:balance WHERE ACCOUNT_NUMBER=:accountNumber ", nativeQuery = true)
	public void updateSaldoPengirim(@Param("balance") double balance, int accountNumber);

}
