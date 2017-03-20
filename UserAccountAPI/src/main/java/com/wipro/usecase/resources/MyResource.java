package com.wipro.usecase.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.wipro.usecase.models.AccountDetails;
import com.wipro.usecase.models.DepositDetails;
import com.wipro.usecase.models.PersonalDetails;
import com.wipro.usecase.services.UserAccountServices;
import com.wipro.usecase.util.Utility;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("users")
public class MyResource {

	UserAccountServices service = new UserAccountServices();

	/**
	 * Method handling HTTP GET requests.
	 * 
	 * @return String that will be returned as a APPLICATION_JSON response.
	 */
	@GET
	@Path(value = "/{userName}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDetailsIfUserExists(@PathParam(value = "userName") String userName,
			@PathParam(value = "password") String password, @Context UriInfo uriInfo) {
		PersonalDetails details = service.getDetailsIfUserExists(userName, password);
		String accountUri = Utility.getUriForAccount(uriInfo, details);
		details.setAccountDetailsUri(accountUri);
		return Response.status(Status.ACCEPTED).entity(details).header("Access-Control-Allow-Origin", "*").build();
	}

	/**
	 * Method handling HTTP @GET requests.
	 * 
	 * @return String that will be returned as a APPLICATION_JSON response.
	 */
	@GET
	@Path(value = "/{customerId}/accounts/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountDetails(@PathParam(value = "customerId") int customerId,
										@PathParam(value = "accountId") int accountId, 
										@Context UriInfo uriInfo) {
		AccountDetails details = service.getAccountDetails(accountId);
		String accountDetailsUri = Utility.getUriForAccount(uriInfo, details);
		details.setAccountDetailsUri(accountDetailsUri);
		return Response.status(Status.ACCEPTED).entity(details).header("Access-Control-Allow-Origin", "*").build();
	}

	/**
	 * Method handling HTTP POST requests.
	 * 
	 * @return String that will be returned as a APPLICATION_JSON response.
	 */
	@POST
	@Path(value = "/accounts/{accountId}/deposits/{amountDeposited}/{paymentMode}/{chequeNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response depositDetails(@PathParam(value = "accountId") int accountId, 
									@PathParam(value = "amountDeposited") int amountDeposited,
									@PathParam(value = "paymentMode") String paymentMode,
									@PathParam(value = "chequeNumber") String chequeNumber,
									@Context UriInfo uriInfo) {
		DepositDetails depositDetails = new DepositDetails(accountId, amountDeposited, paymentMode,chequeNumber);
		service.depositDetails(depositDetails);

		String uri = Utility.getUriForAccount(uriInfo, depositDetails);
		depositDetails.setAccountDetailsUri(uri);
		return Response.status(Status.ACCEPTED).entity(depositDetails).header("Access-Control-Allow-Origin", "*")
				.build();
	}

	/**
	 * Test Data Insertion Method
	 */
	@POST
	@Path(value = "addtestdata")
	public void addTestData() {
		service.addTestData();
	}
}
