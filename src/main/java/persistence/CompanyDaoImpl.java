package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDaoImplementation.
 */
public class CompanyDaoImpl implements CompanyDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.CompanyDAO#findAllCompanies()
	 */
	@Override
	public List<Company> findAllCompanies() {

		List<Company> companies = new ArrayList<Company>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connexion = DaoFactory.INSTANCE.getConnection();
			statement = connexion.createStatement();
			resultSet = statement.executeQuery("SELECT id, name FROM company;");

			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String name = resultSet.getString("name");
				Company company = new Company();
				company.setId(id);
				company.setName(name);
				companies.add(company);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.CompanyDAO#findCompanyById(java.lang.Long)
	 */
	@Override
	public Company findCompanyById(Long id) {
		Company company = new Company();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connexion = DaoFactory.INSTANCE.getConnection();
			statement = connexion.createStatement();
			resultSet = statement
					.executeQuery("SELECT id, name FROM company WHERE id=" + id
							+ ";");
			if (resultSet.next()) {
				Long idc = resultSet.getLong("id");
				String name = resultSet.getString("name");
				company.setId(idc);
				company.setName(name);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return company;
	}

}