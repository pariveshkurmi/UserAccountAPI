package com.wipro.usecase.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
public class AccountDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -299822272265361490L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	/*private String branch;
	private String Ifsc;
	private int zipCode;*/
	private int totalAmount;

	@Transient
	private String priviledge;

	@Transient
	private int customerId;

	@Transient
	private String accountDetailsUri;

	public AccountDetails() {
		super();
	}

	public AccountDetails(int totalAmount) {
		super();
		this.totalAmount = totalAmount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getPriviledge() {
		return priviledge;
	}

	public void setPriviledge(String priviledge) {
		this.priviledge = priviledge;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAccountDetailsUri() {
		return accountDetailsUri;
	}

	public void setAccountDetailsUri(String accountDetailsUri) {
		this.accountDetailsUri = accountDetailsUri;
	}

}
