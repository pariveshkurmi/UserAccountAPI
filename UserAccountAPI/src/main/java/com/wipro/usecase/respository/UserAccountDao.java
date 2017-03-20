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

	// Methods for adding Test Data
	public void addTestData() {
		PersonalDetails userDetails = new PersonalDetails("Bhawesh Kurmi", "D-102, montvert Tropez, Kaspatewasti",
				new Date(), "Pune", "Maharashtra", "India", 1, "bkurmi", "password", "User");
		AccountDetails accountDetails = new AccountDetails(12356, "ICICI Wakad", "ICICI00005", 411057, 45454);

		Session session = HibernateUtility.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(userDetails);
		session.save(accountDetails);
		session.getTransaction().commit();
		session.close();
	}

}
