package com.atm_rest_springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atm_rest_springboot.entity.Account;
import com.atm_rest_springboot.entity.CekSaldoView;
import com.atm_rest_springboot.repository.AccountRepo;
import com.atm_rest_springboot.repository.CekSaldoRepo;
import com.atm_rest_springboot.service.AtmRestSpringbootService;

@Controller
public class RestController {

	@Autowired
	AtmRestSpringbootService atmRestSpringbootService;
	@Autowired
	CekSaldoRepo cekSaldoRepo;
	@Autowired
	AccountRepo accountRepo;

	@GetMapping("/account/{account_number}")
	public ResponseEntity<CekSaldoView> cekSaldoRest(@PathVariable("account_number") int accountNumber) {

		Optional<CekSaldoView> saldo = cekSaldoRepo.findByAccountNumber(accountNumber);
		if (saldo.isPresent()) {

			return new ResponseEntity<>(saldo.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/account/{account_pengirim}/transfer")
	public ResponseEntity<Account> kirimSaldoRest(@PathVariable("account_pengirim") int accountPengirim,
			@RequestBody Account request) {

		try {
			double saldoAwalPengirim = atmRestSpringbootService.saldoAwalPengirim(accountPengirim);
			if (request.getBalance() <= saldoAwalPengirim) {
				double tmpSaldoPengirim = saldoAwalPengirim - request.getBalance();
				if (accountPengirim != request.getAccountNumber()) {

					Account validate = atmRestSpringbootService.kirimSaldoSvc(request);

					if (validate != null) {
						atmRestSpringbootService.updateSaldoPengirim(tmpSaldoPengirim, accountPengirim);
						return new ResponseEntity<>(HttpStatus.CREATED);

					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}

				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
