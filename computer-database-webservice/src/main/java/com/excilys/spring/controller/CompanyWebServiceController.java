/*
 * 
 */
package com.excilys.spring.controller;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

import com.excilys.models.Company;
import com.excilys.service.ServiceCompany;


// TODO: Auto-generated Javadoc
/**
 * The Class CompanyWebServiceController.
 */
@Controller
public class CompanyWebServiceController  {

	/** The Constant GET_COMPANIES. */
	public static  final String GET_COMPANIES = "/sc/companies/listt";
	
	/** The Constant DELETE_COMPANY. */
	public static  final String DELETE_COMPANY = "/sc/companies/delete/{id}";
	
	/** The Constant GET_COMPANIES_PAGE. */
	public static  final String GET_COMPANIES_PAGE = "/sc/companies/listpage";
	
	/** The service company. */
	@Autowired
	private ServiceCompany serviceCompany;

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(CompanyWebServiceController.class);

	/**
	 * Find all companies.
	 *
	 * @return the list
	 */
	@RequestMapping(value=GET_COMPANIES, method = RequestMethod.GET)
	public @ResponseBody List<Company> findAllCompanies() {
		logger.info("[GET] - List all companies");
		return serviceCompany.findAllCompanies();
	}
	
	/**
	 * Delete company.
	 *
	 * @param id the id
	 * @return the response
	 */
	@RequestMapping(value=DELETE_COMPANY, method = RequestMethod.DELETE)
	public @ResponseBody Response deleteCompany(@PathVariable("id") Long id) {
		Company company = serviceCompany.findCompanyById(id);
		serviceCompany.deleteCompany(company);
		logger.info("[DELETE] - Delete company with id :"+id);
		return Response.status(201).entity("[Companies] - Delete company - ok")
				.build();
	}

	/**
	 * Find all companies quota.
	 *
	 * @param limit the limit
	 * @param offset the offset
	 * @return the list
	 */
	@RequestMapping(value=GET_COMPANIES_PAGE, method = RequestMethod.GET)
	public @ResponseBody List<Company> findAllCompaniesQuota(
			@DefaultValue("5") @QueryParam("limit") int limit,
			@DefaultValue("0") @QueryParam("offset") int offset) {
		logger.info("[GET] - List all companies by page");
		return serviceCompany.findAllCompanies(limit, offset);
	}

}
