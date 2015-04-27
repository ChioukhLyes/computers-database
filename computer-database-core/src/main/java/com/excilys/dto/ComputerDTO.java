package com.excilys.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.excilys.utils.LocalDateAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDTO.
 */
@Component
@Entity
@Table(name = "computer")
public class ComputerDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/** The name. */
	@NotNull(message = "{validation.notNull}")
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

	/** The company name. */
	@Transient
	private String companyName;

	/** The company_id. */
	@Column(name = "company_id")
	private Long company_id;

	/**
	 * Instantiates a new computer dto.
	 */
	public ComputerDTO() {
		super();
	}

	/**
	 * Instantiates a new computer dto.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param introduced
	 *            the introduced
	 * @param discontinued
	 *            the discontinued
	 * @param companyId
	 *            the company id
	 */
	public ComputerDTO(long id, String name, LocalDate introduced,
			LocalDate discontinued, Long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = companyId;
	}

	/**
	 * Instantiates a new computer dto.
	 *
	 * @param name
	 *            the name
	 * @param introduced
	 *            the introduced
	 * @param discontinued
	 *            the discontinued
	 * @param companyId
	 *            the company id
	 */
	public ComputerDTO(String name, LocalDate introduced,
			LocalDate discontinued, Long companyId) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = companyId;
	}

	/**
	 * Instantiates a new computer dto.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param introduced
	 *            the introduced
	 * @param discontinued
	 *            the discontinued
	 * @param companyName
	 *            the company name
	 */
	public ComputerDTO(long id, String name, LocalDate introduced,
			LocalDate discontinued, String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyName = companyName;
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
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName
	 *            the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public Long getCompanyId() {
		return company_id;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId
	 *            the new company id
	 */
	public void setCompanyId(Long companyId) {
		this.company_id = companyId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", companyName=" + companyName + ", companyId=" + company_id
				+ "]";
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
		result = prime * result
				+ ((company_id == null) ? 0 : company_id.hashCode());
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
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
		ComputerDTO other = (ComputerDTO) obj;
		if (company_id == null) {
			if (other.company_id != null)
				return false;
		} else if (!company_id.equals(other.company_id))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
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

}
