package com.raquelmc.springApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.raquelmc.springApp.model.Statistic;

/**
 * The Interface StatisticRepository.
 */
public interface StatisticRepository extends MongoRepository<Statistic, String> {

}
