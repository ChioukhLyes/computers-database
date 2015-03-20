package persistence;

import java.util.List;

import model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDAO.
 */
public interface CompanyDAO {

	/**
	 * Find all companies.
	 *
	 * @return the list
	 */
	List<Company> findAllCompanies();
	
	/**
	 * Find all companies.
	 *
	 * @param fromIndex the from index
	 * @param toIndex the to index
	 * @return the list
	 */
	List<Company> findAllCompanies(int fromIndex, int toIndex);

	/**
	 * Find company by id.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	Company findCompanyById(Long id);
	
	
	/**
	 * Gets the count companies.
	 *
	 * @return the count companies
	 */
	int getCountCompanies();

}
