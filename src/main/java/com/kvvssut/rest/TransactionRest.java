package com.kvvssut.rest;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.kvvssut.dto.RestRequestDTO;
import com.kvvssut.interceptors.AmountValidatorInterceptor;
import com.kvvssut.interceptors.PrefixInterceptor;
import com.kvvssut.service.RestService;

@Stateless
@Path("rest")
@Interceptors({ AmountValidatorInterceptor.class, PrefixInterceptor.class })
public class TransactionRest {

	@Inject
	private RestService restService;

	@PUT
	@Path("/method/{param}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response method(@PathParam("param") String userName, RestRequestDTO restRequestDTO) {
		String successful = null;
		try {
			successful = restService.checkDetails(userName, restRequestDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("exception").build();
		}
		return Response.status(Status.OK).entity(successful).build();
	}

	@GET
	@Path("/method/{param}/balance")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBalanceByUsername(@PathParam("param") String userName) {
		String output = String.format("Balance for user %s is: ", userName);
		BigDecimal balance = new BigDecimal(0);
		try {
			balance = restService.getBalance(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
			          .entity("Extremely Sorry!! DB Issues.").build();
		}
		return Response.status(Status.OK).entity(output + balance).build();
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBalanceByUsername() {
		System.out.println("hitting");
		return Response.status(Status.OK).entity("fine").build();
	}
}
