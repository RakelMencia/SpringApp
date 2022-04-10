package com.raquelmc.springApp.model;

import com.fasterxml.jackson.databind.JsonNode;

// TODO: Auto-generated Javadoc
/**
 * The Class Datastream.
 */
public class Datastream {
	
	/** The id. */
	private String id;
	
	/** The feed. */
	private String feed;
	
	/** The datapoints. */
	private JsonNode datapoints;

	/**
	 * Instantiates a new datastream.
	 */
	public Datastream() {
	}

	/**
	 * Instantiates a new datastream.
	 *
	 * @param id the id
	 * @param feed the feed
	 * @param datapoints the datapoints
	 */
	public Datastream(String id, String feed, JsonNode datapoints) {
		super();
		this.id = id;
		this.feed = feed;
		this.datapoints = datapoints;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the feed.
	 *
	 * @return the feed
	 */
	public String getFeed() {
		return feed;
	}

	/**
	 * Sets the feed.
	 *
	 * @param feed the new feed
	 */
	public void setFeed(String feed) {
		this.feed = feed;
	}

	/**
	 * Gets the datapoints.
	 *
	 * @return the datapoints
	 */
	public JsonNode getDatapoints() {
		return datapoints;
	}

	/**
	 * Sets the datapoints.
	 *
	 * @param datapoints the new datapoints
	 */
	public void setDatapoints(JsonNode datapoints) {
		this.datapoints = datapoints;
	}

}
