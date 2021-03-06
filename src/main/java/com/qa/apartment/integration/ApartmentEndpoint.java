package com.qa.apartment.integration;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import com.qa.apartment.business.ApartmentServiceDbImpl;
import com.qa.apartment.util.JSONUtil;

@Path("/apartment")
//@Produces("application/json")
public class ApartmentEndpoint {
	private static final Logger LOGGER = Logger.getLogger(ApartmentEndpoint.class);

	@Inject
	private JSONUtil jsonUtil;

	@Inject
	private ApartmentServiceDbImpl service;

	@GET
	@Path("/json/{id}")
	public String getApartment(@PathParam("id") Long id) {
		return jsonUtil.getJSONForObject(service.findApartment(id));
	}

	@GET
	@Path("/json")
	public String getAllApartments() {
		return service.findAllApartments();
	}
	
	@POST
	@Path("/json")
	//@Consumes(MediaType.APPLICATION_JSON)
	public String createApartment(String newAp) {
		LOGGER.info("in ApartmentEndpoint the value of string is  " + newAp);
		String toReturn;
		try {
		toReturn = service.createApartment(newAp);
		} catch (Exception e) {
			toReturn = "Something went wrong. " + e ;
		}
		return "{ \"message\" : \"" + toReturn + "\"}";
	}
	
	@DELETE
	@Path("/json/{id}")
	public String deleteApartment(@PathParam("id") Long id) {
		return service.deleteApartment(id);
	}

	@PUT
	@Path("/json/{id}")
	@Consumes("application/json")
	public String updateApartment(String newAp, @PathParam("id") Long id) {
		return service.updateApartment(id, newAp);
	}

}
