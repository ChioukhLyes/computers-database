package com.excilys.models;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Page.
 *
 * @param <T>
 *            the generic type
 */
public class Page<T> {

	/** The entities. */
	private List<T> entities;

	/** The page number. */
	private int pageNumber;

	/** The page size. */
	private int pageSize;

	/** The max page. */
	private long maxPage;

	/** The search string. */
	private String searchString;

	/** The order entities by. */
	private String orderEntitiesBy;

	/** The optionOrder. */
	private String optionOrder;

	/**
	 * Instantiates a new page.
	 */
	public Page() {
		super();
	}

	/**
	 * Instantiates a new page.
	 *
	 * @param entities
	 *            the entities
	 * @param pageNumber
	 *            the page number
	 * @param pageSize
	 *            the page size
	 * @param maxPage
	 *            the max page
	 * @param searchString
	 *            the search string
	 * @param orderEntitiesBy
	 *            the order entities by
	 * @param optionOrder
	 *            the optionOrder
	 */
	public Page(List<T> entities, int pageNumber, int pageSize, int maxPage,
			String searchString, String orderEntitiesBy, String optionOrder) {
		super();
		this.entities = entities;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.maxPage = maxPage;
		this.searchString = searchString;
		this.orderEntitiesBy = orderEntitiesBy;
		this.optionOrder = optionOrder;
	}

	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	public List<T> getEntities() {
		return entities;
	}

	/**
	 * Sets the entities.
	 *
	 * @param entities
	 *            the new entities
	 */
	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	/**
	 * Delete from entities.
	 *
	 * @param object
	 *            the object
	 * @return true, if successful
	 */
	public boolean deleteFromEntities(Object object) {
		if (this.entities != null) {
			this.entities.remove(object);
			return true;
		}
		return false;
	}

	/**
	 * Gets the page number.
	 *
	 * @return the page number
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * Sets the page number.
	 *
	 * @param pageNumber
	 *            the new page number
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * Gets the page size.
	 *
	 * @return the page size
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the page size.
	 *
	 * @param pageSize
	 *            the new page size
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Gets the max page.
	 *
	 * @return the max page
	 */
	public long getMaxPage() {
		return maxPage;
	}

	/**
	 * Sets the max page.
	 *
	 * @param maxPage
	 *            the new max page
	 */
	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}

	/**
	 * Gets the search string.
	 *
	 * @return the search string
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * Sets the search string.
	 *
	 * @param searchString
	 *            the new search string
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * Gets the order entities by.
	 *
	 * @return the order entities by
	 */
	public String getOrderEntitiesBy() {
		return orderEntitiesBy;
	}

	/**
	 * Sets the order entities by.
	 *
	 * @param orderEntitiesBy
	 *            the new order entities by
	 */
	public void setOrderEntitiesBy(String orderEntitiesBy) {
		this.orderEntitiesBy = orderEntitiesBy;
	}

	/**
	 * Gets the optionOrder.
	 *
	 * @return the optionOrder
	 */
	public String getoptionOrder() {
		return optionOrder;
	}

	/**
	 * Sets the optionOrder.
	 *
	 * @param optionOrder
	 *            the new optionOrder
	 */
	public void setoptionOrder(String optionOrder) {
		this.optionOrder = optionOrder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Page [entities=" + entities + ", pageNumber=" + pageNumber
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage
				+ ", searchString=" + searchString + ", orderEntitiesBy="
				+ orderEntitiesBy + ", optionOrder=" + optionOrder + "]";
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
				+ ((entities == null) ? 0 : entities.hashCode());
		result = (int) (prime * result + maxPage);
		result = prime * result
				+ ((optionOrder == null) ? 0 : optionOrder.hashCode());
		result = prime * result
				+ ((orderEntitiesBy == null) ? 0 : orderEntitiesBy.hashCode());
		result = prime * result + pageNumber;
		result = prime * result + pageSize;
		result = prime * result
				+ ((searchString == null) ? 0 : searchString.hashCode());
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
		Page<?> other = (Page<?>) obj;
		if (entities == null) {
			if (other.entities != null)
				return false;
		} else if (!entities.equals(other.entities))
			return false;
		if (maxPage != other.maxPage)
			return false;
		if (optionOrder == null) {
			if (other.optionOrder != null)
				return false;
		} else if (!optionOrder.equals(other.optionOrder))
			return false;
		if (orderEntitiesBy == null) {
			if (other.orderEntitiesBy != null)
				return false;
		} else if (!orderEntitiesBy.equals(other.orderEntitiesBy))
			return false;
		if (pageNumber != other.pageNumber)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (searchString == null) {
			if (other.searchString != null)
				return false;
		} else if (!searchString.equals(other.searchString))
			return false;
		return true;
	}

}
