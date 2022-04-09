package com.raquelmc.springApp;

import java.util.ArrayList;
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
		double mediana = 0d;
		int mitad = sortedList.size() / 2;
		// Si la longitud es par, se promedian los del centro
		if (sortedList.size() % 2 == 0) {// número par de elementos
			mediana = (sortedList.get(mitad - 1) + sortedList.get(mitad)) / 2;
		} else {
			mediana = sortedList.get(mitad);// número impar de elementos
		}
		return mediana;
	}

	private double getMode(List<Double> doubleValues) {
		return doubleValues.stream().reduce(0.0, Double::sum) / doubleValues.size();
	}

	public void save(Statistic document) {
		repository.save(document);
	}
}
