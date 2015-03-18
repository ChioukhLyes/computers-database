package persistence;

import java.util.List;

import model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerDAO.
 */
public interface ComputerDAO {

	/**
	 * Find all computers.
	 *
	 * @return the list
	 */
	List<Computer> findAllComputers();

	/**
	 * Find computer by id.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	Computer findComputerById(Long id);

	/**
	 * Insert computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	boolean insertComputer(Computer computer);

	/**
	 * Delete computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	boolean deleteComputer(Computer computer);

	/**
	 * Update computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	boolean updateComputer(Computer computer);
}
