/*
 * Helper con los métodos de la lógica de negocio
 */
package com.raquelmc.springApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raquelmc.springApp.model.Datastream;
import com.raquelmc.springApp.model.Statistic;
import com.raquelmc.springApp.repository.StatisticRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class Helper.
 */
@Component
public class Helper {
	
	/** The repository. */
	@Autowired
	private StatisticRepository repository;

	/**
	 * Creates the statistic.
	 *
	 * @param info the info
	 * @return the list
	 */
	public List<Statistic> createStatistic(ArrayList<Datastream> info) {
		Statistic statistict = null;
		List<Statistic> fullStatistics = new ArrayList<Statistic>();
		List<Double> doubleListOfValues = null;
		List<List<Map<String, Object>>> fullMapInfo = getRequestValues(info);
		for (List<Map<String, Object>> mapInfo : fullMapInfo) {
			if (info != null && !info.isEmpty()) {
				if (mapInfo != null && !mapInfo.isEmpty()) {
					doubleListOfValues = mapToList(mapInfo);
					if (doubleListOfValues != null && !doubleListOfValues.isEmpty()) {
						statistict = new Statistic();
						statistict.setMean(getMean(doubleListOfValues));
						statistict.setMedian(getMedian(doubleListOfValues));
						statistict.setMode(getMode(doubleListOfValues));
						statistict.setStandardDesviation(getStandardDesviation(doubleListOfValues, statistict.getMean()));
						statistict.setQuartiles(getQuartiles(doubleListOfValues));
						statistict.setMaxValue(getMaxValue(doubleListOfValues));
						statistict.setMinValue(getMinValue(doubleListOfValues));
						fullStatistics.add(statistict);
					}
				}
			}
		}
		return fullStatistics;
	}

	/**
	 * Gets the request values.
	 *
	 * @param info the info
	 * @return the request values
	 */
	private List<List<Map<String, Object>>> getRequestValues(ArrayList<Datastream> info) {
		List<List<Map<String, Object>>> fullResult = new ArrayList<List<Map<String, Object>>>();
		;
		List<Map<String, Object>> result = null;
		ObjectMapper mapper = new ObjectMapper();
		for (Datastream ai : info) {
			JsonNode datapoints = null;
			datapoints = ai.getDatapoints();
			try {
				result = mapper.convertValue(datapoints, new TypeReference<List<Map<String, Object>>>() {
				});
				fullResult.add(result);
			} catch (IllegalArgumentException e) {
				// Controlamos que no se meta un double
				System.out.println(Constants.NAN);
			}
		}
		return fullResult;
	}

	/**
	 * Map to list.
	 *
	 * @param mapValues the map values
	 * @return the list
	 */
	private List<Double> mapToList(List<Map<String, Object>> mapValues) {
		List<Double> list2 = new ArrayList<Double>();
		for (Map<String, Object> map : mapValues) {
			Double value = valueValidator(map);
			if (value == null) {// El valor introducio no es válido por no ser un dato numérico
				System.out.println(Constants.NAN2);
			} else {
				list2.add(value);
			}
		}
		return list2;
	}

	/**
	 * Value validator.
	 *
	 * @param map the map
	 * @return the double
	 */
	private Double valueValidator(Map<String, Object> map) {
		Integer integerValue = null;
		if (map.containsKey(Constants.VALUE) && !(map.get(Constants.VALUE) instanceof String)) {// Cogemos los valores
																								// que no sean String
			if (map.get(Constants.VALUE) instanceof Integer) {// Si es un Integer, lo casteamos a Double
				integerValue = (Integer) map.get(Constants.VALUE);
				return integerValue.doubleValue();
			} else {
				return (Double) map.get(Constants.VALUE);
			}
		} else {
			return null;
		}
	}

	/**
	 * Gets the mean.
	 *
	 * @param doubleValues the double values
	 * @return the mean
	 */
	private double getMean(List<Double> doubleValues) {
		return doubleValues.stream().reduce(0.0, Double::sum) / doubleValues.size();
	}

	/**
	 * Gets the median.
	 *
	 * @param doubleValues the double values
	 * @return the median
	 */
	private double getMedian(List<Double> doubleValues) {
		List<Double> sortedList = doubleValues.stream().sorted().collect(Collectors.toList());
		if (sortedList.size() == 1) {// Controlamos que solo venga un valor
			return sortedList.get(0);
		} else {
			return sortedList.size() % 2 == 0
					? (sortedList.get((sortedList.size() / 2) - 1) + sortedList.get(sortedList.size() / 2)) / 2
					: sortedList.get(sortedList.size() / 2);
		}
	}

	/**
	 * Gets the mode.
	 *
	 * @param doubleValues the double values
	 * @return the mode
	 */
	private List<Double> getMode(List<Double> doubleValues) {
		List<Double> modeValues = new ArrayList<Double>();
		Map<Double, Integer> hmMode = new HashMap<Double, Integer>();
		// Guardamos en un map el valor y las repeticiones que tiene
		int max = 1;
		for (int i = 0; i < doubleValues.size(); i++) {
			if (hmMode.get(doubleValues.get(i)) != null) {
				int count = hmMode.get(doubleValues.get(i));
				count++;
				hmMode.put(doubleValues.get(i), count);
				if (count > max)
					max = count;
			} else {
				hmMode.put(doubleValues.get(i), 1);
			}
		}
		// Ordenamos el map por sus repeticiones
		Map<Double, Integer> sortedMap = hmMode.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		// Obtenemos el valor/valores que más veces se repiten, por si hubiese más de
		// una moda
		int maxRepetitions = sortedMap.entrySet().stream()
				.max((entry1, entry2) -> Integer.compare(entry1.getValue(), entry2.getValue())).get().getValue();
		for (Map.Entry<Double, Integer> entry : sortedMap.entrySet()) {
			if (entry.getValue() == maxRepetitions)
				modeValues.add(entry.getKey());
		}
		return modeValues;
	}

	/**
	 * Gets the standard desviation.
	 *
	 * @param doubleValues the double values
	 * @param mean the mean
	 * @return the standard desviation
	 */
	private double getStandardDesviation(List<Double> doubleValues, double mean) {
		double variance = 0;
		// Primero calculamos la varianza a partir de los valores de la lista
		for (double value : doubleValues) {
			double range = 0;
			range = Math.pow(value - mean, 2);
			variance += range;// Sumamos el rango que hemos calculado de todos los valores para obtener la
								// varianza
		}
		return Math.sqrt(variance / doubleValues.size());
	}

	/**
	 * Gets the quartiles.
	 *
	 * @param doubleValues the double values
	 * @return the quartiles
	 */
	private List<Double> getQuartiles(List<Double> doubleValues) {
		// Método de Moore y McCabe
		double[] quartiles = new double[3];
		double q1 = 0;
		double q2 = 0;
		double q3 = 0;
		List<Double> sortedList = doubleValues.stream().sorted().collect(Collectors.toList());
		q2 = this.getMedian(doubleValues);// Mediana = Q2
		quartiles[1] = q2;
		List<Double> first = new ArrayList<Double>();
		List<Double> second = new ArrayList<Double>();

		for (int i = 0; i < sortedList.size() / 2; i++)
			first.add(sortedList.get(i));

		for (int i = sortedList.size() / 2; i < sortedList.size(); i++)
			second.add(sortedList.get(i));

		if (sortedList.size() % 2 > 0) {// Impar
			second.remove(0);
			if (second.size() > 0) {// Controlamos que solo venga un valor
				q1 = this.getMedian(first);// Mediana = Q1
				q3 = this.getMedian(second);// Mediana = Q3
			} else {
				System.out.println(Constants.QUARTILES_INFO);
			}
		} else if (sortedList.size() % 2 == 0) {// Par
			q1 = this.getMedian(first);// Mediana = Q1
			q3 = this.getMedian(second);// Mediana = Q3
		}
		quartiles[0] = q1;
		quartiles[2] = q3;
		return Arrays.stream(quartiles).boxed().collect(Collectors.toList());
	}

	/**
	 * Gets the max value.
	 *
	 * @param doubleValues the double values
	 * @return the max value
	 */
	private double getMaxValue(List<Double> doubleValues) {
		return doubleValues.stream().max(Comparator.naturalOrder()).get();
	}

	/**
	 * Gets the min value.
	 *
	 * @param doubleValues the double values
	 * @return the min value
	 */
	private double getMinValue(List<Double> doubleValues) {
		return doubleValues.stream().min(Comparator.naturalOrder()).get();
	}

	/**
	 * Save.
	 *
	 * @param document the document
	 */
	public void save(Statistic document) {
		if (document != null) {
			repository.save(document);
			System.out.println(Constants.OK_MONGO);
		} else {
			System.out.println(Constants.KO_MONGO);
		}
	}

	/**
	 * Save all.
	 *
	 * @param document the document
	 */
	public void saveAll(List<Statistic> document) {
		if (document != null && !document.isEmpty()) {
			repository.saveAll(document);
			System.out.println(Constants.OK_MONGO);
		} else {
			System.out.println(Constants.KO_MONGO);
		}
	}
}
