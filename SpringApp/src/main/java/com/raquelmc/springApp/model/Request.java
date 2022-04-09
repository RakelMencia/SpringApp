package com.raquelmc.springApp.model;

import java.util.ArrayList;

/**
 * Esta clase represenat el mensaje en json recibido
 * 
 * @author raque
 *
 */

public class Request {
	private String version;
	private ArrayList<AnalyticsInfo> datastreams;

	public Request() {
	}

	public Request(String version, ArrayList<AnalyticsInfo> datastreams) {
		super();
		this.version = version;
		this.datastreams = datastreams;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ArrayList<AnalyticsInfo> getDatastreams() {
		return datastreams;
	}

	public void setDatastreams(ArrayList<AnalyticsInfo> datastreams) {
		this.datastreams = datastreams;
	}

}
