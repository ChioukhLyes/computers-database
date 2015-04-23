/*
 * 
 */
package com.excilys.spring.controller;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.dto.ComputerDTO;
import com.excilys.service.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerWebServiceController.
 */
@Controller
public class ComputerWebServiceController {

	/** The Constant GET_COMPUTERS. */
	public static final String GET_COMPUTERS = "/sc/computers/list";

	/** The Constant GET_COMPUTERS_PAGE. */
	public static final String GET_COMPUTERS_PAGE = "/sc/computers/listpage";

	/** The Constant GET_COMPUTER_ID. */
	public static final String GET_COMPUTER_ID = "/sc/computers/list/{id}";

	/** The Constant ADD_COMPUTER. */
	public static final String ADD_COMPUTER = "/sc/computers/add";

	/** The Constant UPDATE_COMPUTER. */
	public static final String UPDATE_COMPUTER = "/sc/computers/update";

	/** The Constant DELETE_COMPUTER. */
	public static final String DELETE_COMPUTER = "/sc/computers/delete/{id}";

	/** The service computer. */
	@Autowired
	private ServiceComputer serviceComputer;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the computer dto
	 */
	@RequestMapping(value = GET_COMPUTER_ID, method = RequestMethod.GET)
	public @ResponseBody ComputerDTO findById(@PathVariable("id") Long id) {
		return serviceComputer.findComputerById(id);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@RequestMapping(value = GET_COMPUTERS, method = RequestMethod.GET)
	public @ResponseBody List<ComputerDTO> findAll() {
		return serviceComputer.findAllComputers();
	}

	/**
	 * Adds the computer.
	 *
	 * @param computerDTO the computer dto
	 * @return the response
	 */
	@RequestMapping(value = ADD_COMPUTER, method = RequestMethod.POST)
	public @ResponseBody Response addComputer(
			@RequestBody ComputerDTO computerDTO) {
		serviceComputer.insertComputer(computerDTO);
		return Response.status(201).entity("[Computers] - Add computer - ok")
				.build();
	}

	/**
	 * Update computer.
	 *
	 * @param computerDTO the computer dto
	 * @return the response
	 */
	@RequestMapping(value = UPDATE_COMPUTER, method = RequestMethod.PUT)
	public @ResponseBody Response updateComputer(
			@RequestBody ComputerDTO computerDTO) {
		serviceComputer.updateComputer(computerDTO);
		return Response.status(201)
				.entity("[Computers] - Update computer - ok").build();
	}

	/**
	 * Delete computer.
	 *
	 * @param id the id
	 * @return the response
	 */
	@RequestMapping(value = DELETE_COMPUTER, method = RequestMethod.DELETE)
	public @ResponseBody Response deleteComputer(@PathVariable("id") Long id) {
		ComputerDTO computerDTO = serviceComputer.findComputerById(id);
		serviceComputer.deleteComputer(computerDTO);
		return Response.status(201)
				.entity("[Computers] - Delete computer - ok").build();

	}

	/**
	 * Find all computers quota.
	 *
	 * @param limit the limit
	 * @param offset the offset
	 * @return the list
	 */
	@RequestMapping(value = GET_COMPUTERS_PAGE, method = RequestMethod.GET)
	public @ResponseBody List<ComputerDTO> findAllComputersQuota(
			@DefaultValue("5") @QueryParam("limit") int limit,
			@DefaultValue("0") @QueryParam("offset") int offset) {
		return serviceComputer.findAllComputers(limit, offset);
	}

}
