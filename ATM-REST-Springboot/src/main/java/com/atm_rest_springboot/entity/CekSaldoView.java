package com.atm_rest_springboot.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;

@Entity
@Table(name = "CEK_SALDO_VIEW")
public class CekSaldoView implements Serializable {
	@Id
	@Column(name = "ACCOUNT_NUMBER")
	private int accountNumber;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "BALANCE")
	private BigDecimal balance;

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
