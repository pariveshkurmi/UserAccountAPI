package com.wipro.usecase.respository;

import java.util.Date;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wipro.usecase.models.AccountDetails;
import com.wipro.usecase.models.DepositDetails;
import com.wipro.usecase.models.ErrorMessage;
import com.wipro.usecase.models.FundTranferDetails;
import com.wipro.usecase.models.PersonalDetails;
import com.wipro.usecase.util.HibernateUtility;

public class UserAccountDao {

	@SuppressWarnings("unchecked")
	public PersonalDetails getDetailsIfUserExists(String userName, String password) {

		Session session = HibernateUtility.getSessionFactory().openSession();
		session.beginTransaction();
		List<PersonalDetails> details = session.createCriteria(PersonalDetails.class)
				.add(Restrictions.eq("userName", userName)).list();
		session.getTransaction().commit();
		session.close();
		if (details.size() > 0 && userName.equals(details.get(0).getUserName())
				&& password.equalsIgnoreCase(details.get(0).getPassword())) {
			return details.get(0);

		} else {
			ErrorMessage errorMessage = new ErrorMessage(404, "You have Entered wrong id and Password");
			Response response = Response.status(Status.NOT_FOUND).entity(errorMessage)
					.header("Access-Control-Allow-Origin", "*").build();
			throw new WebApplicationException(response);
		}

	}

	public AccountDetails getAccountDetails(int accountId) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		PersonalDetails personalDetails = (PersonalDetails) session.get(PersonalDetails.class, accountId);
		AccountDetails accountDetails = (AccountDetails) session.get(AccountDetails.class, accountId);
		accountDetails.setPriviledge(personalDetails.getPriviledge());
		accountDetails.setCustomerId(personalDetails.getCustomerId());
		return (AccountDetails) session.get(AccountDetails.class, accountId);
	}

	public void depositDetails(DepositDetails depositDetails) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(depositDetails);

		// update the total amount in AccountDetails table
		AccountDetails accountDetails = (AccountDetails) session.get(AccountDetails.class,
				depositDetails.getAccountId());
		PersonalDetails personalDetails = (PersonalDetails) session.get(PersonalDetails.class,
				depositDetails.getAccountId());
		if (accountDetails != null) {
			accountDetails.setPriviledge(personalDetails.getPriviledge());
			depositDetails.setPriviledge(personalDetails.getPriviledge());
			accountDetails.setTotalAmount(accountDetails.getTotalAmount() + depositDetails.getAmountDeposited());
			depositDetails.setCustomerId(personalDetails.getCustomerId());
			depositDetails.setTransactionDate(new Date());
		} else {
			ErrorMessage errorMessage = new ErrorMessage(404,
					"Invalid account number. Please enter valid account number.");
			Response response = Response.status(Status.NOT_FOUND).entity(errorMessage)
					.header("Access-Control-Allow-Origin", "*").build();
			throw new WebApplicationException(response);
		}

		session.getTransaction().commit();
		session.close();
	}

	public void fundTransfer(FundTranferDetails fundTransferDetails) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(fundTransferDetails);
		
		// update the total amount in AccountDetails table
		AccountDetails accountDetails = (AccountDetails) session.get(AccountDetails.class,
				fundTransferDetails.getFromAccountId());
		if(accountDetails.getTotalAmount()>= Integer.parseInt(fundTransferDetails.getAmountTransfered())){
			accountDetails.setTotalAmount(accountDetails.getTotalAmount() - Integer.parseInt(fundTransferDetails.getAmountTransfered()));
		}
		else{
			ErrorMessage errorMessage = new ErrorMessage(404,
					"Insufficient Balance in your Account");
			Response response = Response.status(Status.CONFLICT).entity(errorMessage)
					.header("Access-Control-Allow-Origin", "*").build();
			throw new WebApplicationException(response);
		}
		session.getTransaction().commit();
		session.close();
	}
	// Methods for adding Test Data
	public int addCustomerData(PersonalDetails personalDetails) {
		AccountDetails accountDetails = new AccountDetails(0);
		Session session = HibernateUtility.getSessionFactory().openSession();
		session.beginTransaction();
		int accountId = (int)session.save(accountDetails);
		personalDetails.setAccountId(accountId);
		personalDetails.setPriviledge("User");
		session.save(personalDetails);
		session.getTransaction().commit();
		session.close();
		return accountId;
	}

	public static void main(String[] args) {
		UserAccountDao a = new UserAccountDao();
		a.addTestData1111();
		
	}

	public List<FundTranferDetails> getTransactionList(int fromAccountId) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		 @SuppressWarnings("unchecked")
		List<FundTranferDetails> fundTranferDetailsList = session.createQuery("FROM FundTranferDetails where fromAccountId = :fromAccountId")
				 	.setParameter("fromAccountId", fromAccountId)
				 	.setFirstResult(0)
				 	.setMaxResults(10).list();
		 System.out.println("Size of Deposit details"+fundTranferDetailsList.size());
		return fundTranferDetailsList;
	}

	
	
	// Methods for adding Test Data
		public void addTestData1111() {
			FundTranferDetails userDetails = new FundTranferDetails(1234,3456,"898786","Tranferred",new Date());
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(userDetails);
			session.getTransaction().commit();
			session.close();
		}
}
