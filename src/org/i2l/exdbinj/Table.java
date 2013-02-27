package org.i2l.exdbinj;

import java.util.HashMap;
import java.util.Map;

public class Table {
	private String name;
	private Map<String, String> values = new HashMap<String, String>();

	public Table(String name) {
		this.name = name;
	}

	public Table(String name, Map<String, String> values) {
		this.name = name;
		setValues(values);
	}

	/**
	 * @return the values
	 */
	public Map<String, String> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public void addValue(String name, String value) {
		values.put(name, value);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public String toString() {
		return name + " " + values;
	}
}
