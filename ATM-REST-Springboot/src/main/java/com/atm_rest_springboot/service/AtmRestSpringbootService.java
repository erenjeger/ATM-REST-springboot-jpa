package com.atm_rest_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm_rest_springboot.entity.Account;
import com.atm_rest_springboot.repository.AccountRepo;
import com.atm_rest_springboot.repository.CekSaldoRepo;

@Service
public class AtmRestSpringbootService {
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	CekSaldoRepo ceksaldoRepo;

	public double saldoAwalPengirim(int accountNumber) {
		return accountRepo.cekSaldoAwal(accountNumber);
	}

	public double saldoAwalPenerima(int accountNumber) {
		return accountRepo.cekSaldoAwal(accountNumber);
	}

	public void updateSaldoPengirim(double balance, int accountNumber) {
		accountRepo.updateSaldoPengirim(balance, accountNumber);
	}

	
	public Account kirimSaldoSvc(Account request) {
		double saldoAwalPenerima = saldoAwalPenerima(request.getAccountNumber());

		Account account = accountRepo.findByAccountNumber(request.getAccountNumber()).orElse(null);

		double tmpSaldoPenerima = request.getBalance() + saldoAwalPenerima;
		
		account.setBalance(tmpSaldoPenerima);
		return accountRepo.save(account);

	}

}
