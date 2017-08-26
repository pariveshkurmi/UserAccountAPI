package com.wipro.usecase.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class DepositDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	@Transient
	private int customerId;
	private int accountId;
	private int amountDeposited;
	private String paymentMode;
	private String chequeNumber;
	private Date transactionDate;
	@Transient
	private String accountDetailsUri;
	@Transient
	private String priviledge;

	public DepositDetails() {
		super();
	}

	public DepositDetails(int accountId, int amountDeposited, String paymentMode, String chequeNumber) {
		super();
		this.accountId = accountId;
		this.amountDeposited = amountDeposited;
		this.paymentMode = paymentMode;
		this.chequeNumber = chequeNumber;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public String getAccountDetailsUri() {
		return accountDetailsUri;
	}

	public void setAccountDetailsUri(String accountDetailsUri) {
		this.accountDetailsUri = accountDetailsUri;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAmountDeposited() {
		return amountDeposited;
	}

	public void setAmountDeposited(int amountDeposited) {
		this.amountDeposited = amountDeposited;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public String getPriviledge() {
		return priviledge;
	}

	public void setPriviledge(String priviledge) {
		this.priviledge = priviledge;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
