package com.excilys.webservice.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.service.ServiceComputer;
import com.excilys.webservice.ComputerWebService;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerWebServiceImpl.
 */
@Path("/computers")
public class ComputerWebServiceImpl implements ComputerWebService {

	/** The service computer. */
	@Autowired
	private ServiceComputer serviceComputer;

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(CompanyWebServiceImpl.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.webservice.ComputerWebService#findById(java.lang.Long)
	 */
	@GET
	@Path("/list/{id: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public ComputerDTO findById(@PathParam("id") Long id) {
		logger.info("[GET] - List computer with id :"+id);
		return serviceComputer.findComputerById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.webservice.ComputerWebService#findAll()
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<ComputerDTO> findAll() {
		logger.info("[GET] - List all computers");
		return serviceComputer.findAllComputers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.webservice.ComputerWebService#addComputer(com.excilys.dto
	 * .ComputerDTO)
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response addComputer(ComputerDTO computerDTO) {
		serviceComputer.insertComputer(computerDTO);
		logger.info("[POST] - Add computer");
		return Response.status(201).entity("[Computers] - Add computer - ok")
				.build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.webservice.ComputerWebService#updateComputer(com.excilys.
	 * dto.ComputerDTO)
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response updateComputer(ComputerDTO computerDTO) {
		serviceComputer.updateComputer(computerDTO);
		logger.info("[PUT] - Update computer with id :"+computerDTO.getId());
		return Response.status(201)
				.entity("[Computers] - Update computer - ok").build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.webservice.ComputerWebService#deleteComputer(java.lang.Long)
	 */
	@Path("/delete/{id: [0-9]+}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response deleteComputer(@PathParam("id") Long id) {
		ComputerDTO computerDTO = serviceComputer.findComputerById(id);
		serviceComputer.deleteComputer(computerDTO);
		logger.info("[DELETE] - Delete computer with id :"+id);
		return Response.status(201)
				.entity("[Computers] - Delete computer - ok").build();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.webservice.ComputerWebService#findAllComputersQuota(int,
	 * int)
	 */
	@GET
	@Path("/listpage")
	@Override
	public List<ComputerDTO> findAllComputersQuota(
			@DefaultValue("5") @QueryParam("limit") int limit,
			@DefaultValue("0") @QueryParam("offset") int offset) {
		logger.info("[GET] - List computers by page");
		return serviceComputer.findAllComputers(limit, offset);
	}

}
