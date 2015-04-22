/*
 * 
 */
package com.excilys.webservice.impl;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.models.Company;
import com.excilys.service.ServiceCompany;
import com.excilys.webservice.CompanyWebService;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyWebServiceImpl.
 */
@Path("/companies")
@Produces({ MediaType.APPLICATION_JSON})
public class CompanyWebServiceImpl implements CompanyWebService {

	/** The service company. */
	@Autowired
	private ServiceCompany serviceCompany;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.webservice.CompanyWebService#findAllCompanies()
	 */
	@GET
	@Path("/list")
	@Override
	public List<Company> findAllCompanies() {
		return serviceCompany.findAllCompanies();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.webservice.CompanyWebService#deleteCompany(com.excilys.models
	 * .Company)
	 */
	@Path("/delete/{id: [0-9]+}")
	@DELETE
	@Override
	public Response deleteCompany(@PathParam("id") Long id) {
		Company company = serviceCompany.findCompanyById(id);
		serviceCompany.deleteCompany(company);
		return Response.status(201).entity("[Companies] - Delete company - ok")
				.build();
	}

}
