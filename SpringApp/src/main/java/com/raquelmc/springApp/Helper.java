package com.raquelmc.springApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raquelmc.springApp.model.AnalyticsInfo;
import com.raquelmc.springApp.model.Statistic;
import com.raquelmc.springApp.repository.StatisticRepository;

@Component
public class Helper {
	@Autowired
	private StatisticRepository repository;

	private static Logger log = Logger.getLogger(Helper.class);

	public Statistic createStatistic(ArrayList<AnalyticsInfo> info) {
		BasicConfigurator.configure();
		Statistic statistict = null;
		List<Double> doubleListOfValues = null;
		if (info != null && !info.isEmpty()) {
			statistict = new Statistic();
			doubleListOfValues = mapToList(getRequestValues(info));
			statistict.setMean(getMean(doubleListOfValues));
			statistict.setMedian(getMedian(doubleListOfValues));
			statistict.setMode(getMode(doubleListOfValues));
			statistict.setStandardDesviation(getStandardDesviation(doubleListOfValues, statistict.getMean()));
		}
		return statistict;
	}

	private List<Map<String, Double>> getRequestValues(ArrayList<AnalyticsInfo> info) {

		List<Map<String, Double>> result = null;
		ObjectMapper mapper = new ObjectMapper();
		for (AnalyticsInfo ai : info) {
			JsonNode datapoints = null;
			datapoints = ai.getDatapoints();
			try {
				result = mapper.convertValue(datapoints, new TypeReference<List<Map<String, Double>>>() {
				});
			} catch (IllegalArgumentException e) {
				// Controlamos que no se meta un double
				log.error("Se está introduciendo un valor no numérico");
			}
		}
		return result;
	}

	private List<Double> mapToList(List<Map<String, Double>> mapValues) {
		List<Double> list2 = new ArrayList<Double>();
		for (Map<String, Double> map : mapValues) {
			list2.addAll(new ArrayList<Double>(map.values()));
		}
		return list2;
	}

	private double getMean(List<Double> doubleValues) {
		return doubleValues.stream().reduce(0.0, Double::sum) / doubleValues.size();
	}

	private double getMedian(List<Double> doubleValues) {
		List<Double> sortedList = doubleValues.stream().sorted().collect(Collectors.toList());
		return sortedList.size() % 2 == 0
				? (sortedList.get((sortedList.size() / 2) - 1) + sortedList.get(sortedList.size() / 2)) / 2
				: sortedList.get(sortedList.size() / 2);
	}

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

	public void save(Statistic document) {
		repository.save(document);
	}
}
