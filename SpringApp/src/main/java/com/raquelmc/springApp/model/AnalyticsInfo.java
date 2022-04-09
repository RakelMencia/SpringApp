package com.raquelmc.springApp.model;

import com.fasterxml.jackson.databind.JsonNode;

public class AnalyticsInfo {
	private String id;
	private String feed;
	private JsonNode datapoints;

	public AnalyticsInfo() {
	}

	public AnalyticsInfo(String id, String feed, JsonNode datapoints) {
		super();
		this.id = id;
		this.feed = feed;
		this.datapoints = datapoints;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
	}

	public JsonNode getDatapoints() {
		return datapoints;
	}

	public void setDatapoints(JsonNode datapoints) {
		this.datapoints = datapoints;
	}

}
