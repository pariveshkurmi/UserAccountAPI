package com.wipro.usecase.services;

import java.util.List;

import com.wipro.usecase.models.AccountDetails;
import com.wipro.usecase.models.DepositDetails;
import com.wipro.usecase.models.FundTranferDetails;
import com.wipro.usecase.models.PersonalDetails;
import com.wipro.usecase.respository.UserAccountDao;

public class UserAccountServices {

	UserAccountDao userAccountDao = new UserAccountDao();

	public PersonalDetails getDetailsIfUserExists(String userName, String password) {
		return userAccountDao.getDetailsIfUserExists(userName, password);
	}

	public AccountDetails getAccountDetails( int accountId) {
		return userAccountDao.getAccountDetails(accountId);
	}

	public void depositDetails(DepositDetails depositDetails) {
		 userAccountDao.depositDetails(depositDetails);
	}

	// Methods for Adding Test Data
	public int addCustomerData(PersonalDetails personalDetails) {
		int accountId = userAccountDao.addCustomerData(personalDetails);
		return accountId;
	}

	public List<FundTranferDetails> getTransactionList(int accountId) {
		return userAccountDao.getTransactionList(accountId);
	}

	public void fundTranfer(FundTranferDetails fundTransferDetails) {

		userAccountDao.fundTransfer(fundTransferDetails);
	}

}
