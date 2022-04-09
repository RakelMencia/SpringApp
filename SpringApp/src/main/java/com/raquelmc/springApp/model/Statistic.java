package com.raquelmc.springApp.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Objeto que se persistirá en la bbdd-> Información estadística
 * 
 * @author raquel
 *
 */

@Document(collection = "streaming-analytics")
public class Statistic {

	@Id
	private String id;
	private double mean;// media
	private double median;// mediana
	private double mode;// moda
	private double standardDesviation;
	private double quartiles;
	private double maxValue;
	private double minValue;
	@CreatedDate
	private Instant createdDate;
	@LastModifiedDate
	private Instant lastModifiedDate;

	public Statistic() {
	}

	public Statistic(String id, double mean, double median, double mode, double standardDesviation, double quartiles,
			double maxValue, double minValue, Instant createdDate, Instant lastModifiedDate) {
		super();
		this.id = id;
		this.mean = mean;
		this.median = median;
		this.mode = mode;
		this.standardDesviation = standardDesviation;
		this.quartiles = quartiles;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public double getMode() {
		return mode;
	}

	public void setMode(double mode) {
		this.mode = mode;
	}

	public double getStandardDesviation() {
		return standardDesviation;
	}

	public void setStandardDesviation(double standardDesviation) {
		this.standardDesviation = standardDesviation;
	}

	public double getQuartiles() {
		return quartiles;
	}

	public void setQuartiles(double quartiles) {
		this.quartiles = quartiles;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "Statistic [id=" + id + ", mean=" + mean + ", median=" + median + ", mode=" + mode
				+ ", standardDesviation=" + standardDesviation + ", quartiles=" + quartiles + ", maxValue=" + maxValue
				+ ", minValue=" + minValue + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
				+ "]";
	}

}