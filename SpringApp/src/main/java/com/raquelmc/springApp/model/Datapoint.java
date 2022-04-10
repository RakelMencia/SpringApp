package com.raquelmc.springApp.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Datapoint.
 */
public class Datapoint {
	
	/** The key. */
	private String key;
	
	/** The value. */
	private double value;

	/**
	 * Instantiates a new datapoint.
	 */
	public Datapoint() {
	}

	/**
	 * Instantiates a new datapoint.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public Datapoint(String key, double value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(double value) {
		this.value = value;
	}

}
