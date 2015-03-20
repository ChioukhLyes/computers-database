package model;

import java.time.LocalDateTime;

// TODO: Auto-generated Javadoc
/**
 * The Class Computer.
 */
public class Computer {

	/** The id. */
	private long id;

	/** The name. */
	private String name;

	/** The introduced. */
	private LocalDateTime introduced;

	/** The discontinued. */
	private LocalDateTime discontinued;

	/** The company id. */
	private Long companyId;

	/**
	 * Instantiates a new computer.
	 *
	 * @param name
	 *            the name
	 */
	public Computer(String name) {
		super();
		this.name = name;
	}

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
	 * @param companyId
	 *            the company id
	 */
	public Computer(String name, LocalDateTime introduced,
			LocalDateTime discontinued, Long companyId) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
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
	public LocalDateTime getIntroduced() {
		return introduced;
	}

	/**
	 * Sets the introduced.
	 *
	 * @param introduced
	 *            the new introduced
	 */
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	/**
	 * Gets the discontinued.
	 *
	 * @return the discontinued
	 */
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	/**
	 * Sets the discontinued.
	 *
	 * @param discontinued
	 *            the new discontinued
	 */
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId
	 *            the new company id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		if (this.introduced != null && discontinued != null) {
			return "Computer [id=" + id + ", name=" + name + ", introduced="
					+ introduced.toLocalDate() + ", discontinued="
					+ discontinued.toLocalDate() + ", companyId=" + companyId
					+ "]";
		} else if ((this.introduced == null && discontinued != null)) {
			return "Computer [id=" + id + ", name=" + name + ", introduced="
					+ introduced + ", discontinued="
					+ discontinued.toLocalDate() + ", companyId=" + companyId
					+ "]";
		} else if (this.introduced != null && discontinued == null) {
			return "Computer [id=" + id + ", name=" + name + ", introduced="
					+ introduced.toLocalDate() + ", discontinued="
					+ discontinued + ", companyId=" + companyId + "]";
		} else
			return "Computer [id=" + id + ", name=" + name + ", introduced="
					+ introduced + ", discontinued=" + discontinued
					+ ", companyId=" + companyId + "]";

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
				+ ((companyId == null) ? 0 : companyId.hashCode());
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
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
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
