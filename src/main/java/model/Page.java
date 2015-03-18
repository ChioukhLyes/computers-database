package model;

import java.util.ArrayList;
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
	private List<T> entities = new ArrayList<T>();

	/** The from index. */
	private int fromIndex = 0;

	/** The to index. */
	private int toIndex = 0;

	/**
	 * Paginate.
	 *
	 * @param entites
	 *            the entites
	 * @param fromIndex
	 *            the from index
	 * @param toIndex
	 *            the to index
	 * @return the list
	 */
	public List<T> paginate(List<T> entites, int fromIndex, int toIndex) {

		if (toIndex > entites.size())
			toIndex = entites.size();

		if (fromIndex < 0 || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		return entites.subList(fromIndex, toIndex);
	}

	/**
	 * Show paginated list.
	 *
	 * @param entities
	 *            the entities
	 */
	public void showPaginatedList(List<T> entities) {

		for (int i = 0; i < entities.size(); i++) {
			System.out.println(entities.get(i).toString());
		}
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
	 * Gets the from index.
	 *
	 * @return the from index
	 */
	public int getFromIndex() {
		return fromIndex;
	}

	/**
	 * Sets the from index.
	 *
	 * @param fromIndex
	 *            the new from index
	 */
	public void setFromIndex(int fromIndex) {
		this.fromIndex = fromIndex;
	}

	/**
	 * Gets the to index.
	 *
	 * @return the to index
	 */
	public int getToIndex() {
		return toIndex;
	}

	/**
	 * Sets the to index.
	 *
	 * @param toIndex
	 *            the new to index
	 */
	public void setToIndex(int toIndex) {
		this.toIndex = toIndex;
	}

}
