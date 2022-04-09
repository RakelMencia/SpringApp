package com.raquelmc.springApp.model;

public class Datapoint {
	private String key;
	private double value;

	public Datapoint() {
	}

	public Datapoint(String key, double value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
