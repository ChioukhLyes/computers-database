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
	 * Find company by id.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	Company findCompanyById(Long id);

}
