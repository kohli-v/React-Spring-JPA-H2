package com.wcf.client.Backend.Repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wcf.client.Backend.Entities.Feature;
import com.wcf.client.Backend.Entities.FeatureId;

public interface IManageFeatureRepository extends CrudRepository<Feature,FeatureId> {

	List<Feature> findAll();
	
	void deleteById (FeatureId featureId);
	
	@SuppressWarnings("unchecked")
	Feature save(Feature f);
	
	Optional<Feature> findById(FeatureId featureId);
	
	@Query("select f from Feature f where f.client = ?1 and f.clientPriority >= ?2")
	List<Feature> findByClientAndPriority(  char client,  int priority);
	

}
