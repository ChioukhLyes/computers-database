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

import org.springframework.beans.factory.annotation.Autowired;

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
		return serviceComputer.findAllComputers(limit, offset);
	}

}
