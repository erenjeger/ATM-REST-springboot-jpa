package com.atm_rest_springboot.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.atm_rest_springboot.entity.CekSaldoView;

public interface CekSaldoRepo extends CrudRepository<CekSaldoView, Integer> {
	Optional<CekSaldoView> findByAccountNumber(Integer accountNumber);
}
