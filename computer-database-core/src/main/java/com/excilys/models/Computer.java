/*
 * root
 * 
 * Computer.java - 2015
 */
package com.excilys.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.excilys.utils.LocalDateAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class Computer.
 */
@Component
@Entity
@Table(name = "computer")
public class Computer implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/** The name. */

	@Column(name = "name")
	private String name;

	/** The introduced. */
	@Column(name = "introduced")
	@Type(type = "com.excilys.utils.CustomLocalDateTimeUserType")
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate introduced;

	/** The discontinued. */
	@Column(name = "discontinued")
	@Type(type = "com.excilys.utils.CustomLocalDateTimeUserType")
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate discontinued;

	/** The company. */
	@OneToOne
	private Company company;

	/**
	 * Instantiates a new computer.
	 */
	public Computer() {
		super();
	}

	/**
	 * Instantiates a new computer.
	 *
	 * @param name
	 *            the name
	 * @param introduced
	 *            the introduced
	 * @param discontinued
	 *            the discontinued
	 * @param company
	 *            the company
	 */
	public Computer(String name, LocalDate introduced, LocalDate discontinued,
			Company company) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the introduced.
	 *
	 * @return the introduced
	 */
	public LocalDate getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced
	 *            the new introduced
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued
	 *            the new discontinued
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company
	 *            the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {
		private Computer computer;

		/**
		 * Name.
		 *
		 * @param name
		 *            the name
		 * @return the builder
		 */
		public Builder name(String name) {
			computer.setName(name);
			return this;
		}

		/**
		 * Id.
		 *
		 * @param id
		 *            the id
		 * @return the builder
		 */
		public Builder id(int id) {
			computer.setId(id);
			return this;
		}

		/**
		 * Introduced.
		 *
		 * @param introduced
		 *            the introduced
		 * @return the builder
		 */
		public Builder introduced(LocalDate introduced) {
			computer.setIntroduced(introduced);
			return this;
		}

		/**
		 * Discontinued.
		 *
		 * @param discontinued
		 *            the discontinued
		 * @return the builder
		 */
		public Builder discontinued(LocalDate discontinued) {
			computer.setDiscontinued(discontinued);
			return this;
		}

		/**
		 * Company.
		 *
		 * @param company
		 *            the company
		 * @return the builder
		 */
		public Builder company(Company company) {
			computer.setCompany(company);
			return this;
		}

		/**
		 * Company.
		 *
		 * @param companyId
		 *            the company id
		 * @return the builder
		 */
		public Builder company(long companyId) {
			computer.setCompany(new Company(companyId));
			return this;
		}

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			computer = new Computer();
		}

		/**
		 * Builds the.
		 *
		 * @return the computer
		 */
		public Computer build() {
			return computer;
		}
	}

}
