package com.raquelmc.springApp.model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Esta clase represenat el mensaje en json recibido.
 *
 * @author raque
 */

public class Request {
	
	/** The version. */
	private String version;
	
	/** The device. */
	private String device;
	
	/** The path. */
	private ArrayList<String> path;
	
	/** The trusted boot. */
	private String trustedBoot;
	
	/** The datastreams. */
	private ArrayList<Datastream> datastreams;

	/**
	 * Instantiates a new request.
	 */
	public Request() {
	}

	/**
	 * Instantiates a new request.
	 *
	 * @param version the version
	 * @param device the device
	 * @param path the path
	 * @param trustedBoot the trusted boot
	 * @param datastreams the datastreams
	 */
	public Request(String version, String device, ArrayList<String> path, String trustedBoot,
			ArrayList<Datastream> datastreams) {
		super();
		this.version = version;
		this.device = device;
		this.path = path;
		this.trustedBoot = trustedBoot;
		this.datastreams = datastreams;
	}

	/**
	 * Gets the device.
	 *
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * Sets the device.
	 *
	 * @param device the new device
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public ArrayList<String> getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(ArrayList<String> path) {
		this.path = path;
	}

	/**
	 * Gets the trusted boot.
	 *
	 * @return the trusted boot
	 */
	public String getTrustedBoot() {
		return trustedBoot;
	}

	/**
	 * Sets the trusted boot.
	 *
	 * @param trustedBoot the new trusted boot
	 */
	public void setTrustedBoot(String trustedBoot) {
		this.trustedBoot = trustedBoot;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the datastreams.
	 *
	 * @return the datastreams
	 */
	public ArrayList<Datastream> getDatastreams() {
		return datastreams;
	}

	/**
	 * Sets the datastreams.
	 *
	 * @param datastreams the new datastreams
	 */
	public void setDatastreams(ArrayList<Datastream> datastreams) {
		this.datastreams = datastreams;
	}

}
