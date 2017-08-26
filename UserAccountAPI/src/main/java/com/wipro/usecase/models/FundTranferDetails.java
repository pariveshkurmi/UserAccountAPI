package com.wipro.usecase.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class FundTranferDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	private int fromAccountId;
	private int benificiaryAccountId;
	private String amountTransfered;
	private String remarks;
	private Date transactionDate;
	
	public FundTranferDetails(){}
	public FundTranferDetails(int fromAccountId, int benificiaryAccountId, String amountTransfered, String remarks,
			Date transactionDate) {
		super();
		this.fromAccountId = fromAccountId;
		this.benificiaryAccountId = benificiaryAccountId;
		this.amountTransfered = amountTransfered;
		this.remarks = remarks;
		this.transactionDate = transactionDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public int getBenificiaryAccountId() {
		return benificiaryAccountId;
	}
	public void setBenificiaryAccountId(int benificiaryAccountId) {
		this.benificiaryAccountId = benificiaryAccountId;
	}
	public String getAmountTransfered() {
		return amountTransfered;
	}
	public void setAmountTransfered(String amountTransfered) {
		this.amountTransfered = amountTransfered;
	}
	
}
