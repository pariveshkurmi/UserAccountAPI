package com.wipro.usecase.util;

import javax.ws.rs.core.UriInfo;

import com.wipro.usecase.models.AccountDetails;
import com.wipro.usecase.models.DepositDetails;
import com.wipro.usecase.models.PersonalDetails;
import com.wipro.usecase.resources.MyResource;

public class Utility {
	public static String getUriForAccount(UriInfo uriInfo, PersonalDetails details) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MyResource.class)
				.path(details.getCustomerId()+"")
				.path("accounts")
				.path(details.getAccountId()+"").build().toString();
		return uri;
	}

	public static String getUriForAccount(UriInfo uriInfo, DepositDetails depositDetails) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MyResource.class)
				.path(depositDetails.getCustomerId()+"")
				.path("accounts")
				.path(depositDetails.getAccountId()+"").build().toString();
		return uri;
	}

	public static String getUriForAccount(UriInfo uriInfo, AccountDetails details) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MyResource.class)
				.path(details.getCustomerId()+"")
				.path("accounts")
				.path(details.getAccountId()+"").build().toString();
		return uri;
	}

}
