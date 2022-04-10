package com.raquelmc.springApp.model;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * Objeto que se persistirá en la bbdd-> Información estadística.
 *
 * @author raquel
 */

@Document(collection = "streaming-analytics")
public class Statistic {

	/** The id. */
	@Id
	private String id;
	
	/** The mean. */
	private double mean;// media
	
	/** The median. */
	private double median;// mediana
	
	/** The mode. */
	private List<Double> mode;// moda //Puede haber n modas
	
	/** The standard desviation. */
	private double standardDesviation;
	
	/** The quartiles. */
	private List<Double> quartiles;//3 cuartiles
	
	/** The max value. */
	private double maxValue;
	
	/** The min value. */
	private double minValue;
	
	/** The created date. */
	@CreatedDate
	private Instant createdDate;
	
	/** The last modified date. */
	@LastModifiedDate
	private Instant lastModifiedDate;

	/**
	 * Instantiates a new statistic.
	 */
	public Statistic() {
	}

	/**
	 * Instantiates a new statistic.
	 *
	 * @param id the id
	 * @param mean the mean
	 * @param median the median
	 * @param mode the mode
	 * @param standardDesviation the standard desviation
	 * @param quartiles the quartiles
	 * @param maxValue the max value
	 * @param minValue the min value
	 * @param createdDate the created date
	 * @param lastModifiedDate the last modified date
	 */
	public Statistic(String id, double mean, double median, List<Double> mode, double standardDesviation, List<Double> quartiles,
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
	 * Gets the mean.
	 *
	 * @return the mean
	 */
	public double getMean() {
		return mean;
	}

	/**
	 * Sets the mean.
	 *
	 * @param mean the new mean
	 */
	public void setMean(double mean) {
		this.mean = mean;
	}

	/**
	 * Gets the median.
	 *
	 * @return the median
	 */
	public double getMedian() {
		return median;
	}

	/**
	 * Sets the median.
	 *
	 * @param median the new median
	 */
	public void setMedian(double median) {
		this.median = median;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public List<Double> getMode() {
		return mode;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode the new mode
	 */
	public void setMode(List<Double> mode) {
		this.mode = mode;
	}

	/**
	 * Gets the standard desviation.
	 *
	 * @return the standard desviation
	 */
	public double getStandardDesviation() {
		return standardDesviation;
	}

	/**
	 * Sets the standard desviation.
	 *
	 * @param standardDesviation the new standard desviation
	 */
	public void setStandardDesviation(double standardDesviation) {
		this.standardDesviation = standardDesviation;
	}

	/**
	 * Gets the quartiles.
	 *
	 * @return the quartiles
	 */
	public List<Double> getQuartiles() {
		return quartiles;
	}

	/**
	 * Sets the quartiles.
	 *
	 * @param quartiles the new quartiles
	 */
	public void setQuartiles(List<Double> quartiles) {
		this.quartiles = quartiles;
	}

	/**
	 * Gets the max value.
	 *
	 * @return the max value
	 */
	public double getMaxValue() {
		return maxValue;
	}

	/**
	 * Sets the max value.
	 *
	 * @param maxValue the new max value
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * Gets the min value.
	 *
	 * @return the min value
	 */
	public double getMinValue() {
		return minValue;
	}

	/**
	 * Sets the min value.
	 *
	 * @param minValue the new min value
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Instant getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the last modified date.
	 *
	 * @return the last modified date
	 */
	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * Sets the last modified date.
	 *
	 * @param lastModifiedDate the new last modified date
	 */
	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Statistic [id=" + id + ", mean=" + mean + ", median=" + median + ", mode=" + mode
				+ ", standardDesviation=" + standardDesviation + ", quartiles=" + quartiles + ", maxValue=" + maxValue
				+ ", minValue=" + minValue + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
				+ "]";
	}

}
