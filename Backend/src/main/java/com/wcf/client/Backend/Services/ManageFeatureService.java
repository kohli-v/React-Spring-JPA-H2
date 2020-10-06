package com.wcf.client.Backend.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wcf.client.Backend.Entities.Feature;
import com.wcf.client.Backend.Entities.FeatureId;
import com.wcf.client.Backend.Repositories.IManageFeatureRepository;
import com.wcf.client.Backend.Resources.AddFeatureResource;
import com.wcf.client.Backend.Resources.DeleteFeatureResponseResource;
import com.wcf.client.Backend.Resources.EnableFeatureResponseResource;
import com.wcf.client.Backend.Resources.FeatureStatusList;

@Service 
public class ManageFeatureService implements IManageFeatureService{
	@Autowired IManageFeatureRepository iFeatureRepository;
	
	
	
	@Override
	public List<Feature> retrieveFeatureList() {
		return iFeatureRepository.findAll();
	}

	
	@Override
	public Feature addFeature(AddFeatureResource addFeatureResource) {
		
		
		/**
		 *1. First retrieve list of all features for the same client that have >= priority 
		 *2. Save the incoming feature.
		 *3. Loop over all of the retrieved features and adjust their priority to prevent priority conflict.
		 **/
		
		String title = addFeatureResource.getTitle();
		char client = addFeatureResource.getClient();
		String productArea = addFeatureResource.getProductArea();
		int clientPriority = addFeatureResource.getClientPriority();
		String targetDate = addFeatureResource.getTargetDate();
		String description = addFeatureResource.getDescription();
		String featureStatus = FeatureStatusList.IN_DEVELOPMENT.toString();
		
		
		// Retrieve features with incoming feature priority  >= priority
		List<Feature> listOfFeaturesWithSameOrHigherPriority = 
				iFeatureRepository.findByClientAndPriority('B', 1);
		
		
		// Save the incoming feature
		Feature feature = new Feature( title,  client,  description, productArea,  
				featureStatus, clientPriority, targetDate);
		iFeatureRepository.save(feature);
		
		
		// adjust priority for other features
		int currentPriority = clientPriority;
		for (Feature itemFeature : listOfFeaturesWithSameOrHigherPriority) {
			
			// increment priority if incoming feature priority and store feature priority is the same.
			if (itemFeature.getClientPriority()==currentPriority) {
				
				itemFeature.setClientPriority(itemFeature.getClientPriority()+1);
				iFeatureRepository.save(itemFeature);
				currentPriority = itemFeature.getClientPriority();
			}
		}
		
		return feature;
	}
	
	@Override
	public DeleteFeatureResponseResource deleteFeature(List<FeatureId> featureIdList) {
		
		/**
		 * Delete features except for WILL FAIL feature
		 **/
		
		DeleteFeatureResponseResource response = new DeleteFeatureResponseResource();
		response.setResult("success");
		for (FeatureId item : featureIdList) {
			try {
				if (item.getTitle().equals("WILL FAIL")) {
					throw new Exception ("This will always fail");
				}
				iFeatureRepository.deleteById(item);
			}
			catch(Exception ex) {
				response.setResult("ERROR: "+ex.getMessage());
				FeatureId failedFeatureId = new FeatureId(item.getTitle(),item.getClient());
				response.getFailedToActionFeatures().add(failedFeatureId);
			}
		}
		
		return response;
	}
	
	@Override
	public EnableFeatureResponseResource enableFeature(List<FeatureId> featureIdList) {
		
		/**
		 * Enable features except for WILL FAIL feature
		 **/
		
		EnableFeatureResponseResource response = new EnableFeatureResponseResource();
		response.setResult("success");	
		
		for (FeatureId item : featureIdList) {
			
			try {
				if (item.getTitle().equals("WILL FAIL")) {
					throw new Exception ("This will always fail");
				}
				Optional<Feature> result = iFeatureRepository.findById(item);
				Feature feature = result.get();
				feature.setFeatureStatus(FeatureStatusList.ENABLED.toString());
				iFeatureRepository.save(feature);
				
			}
			
			catch(Exception ex) {
				response.setResult("ERROR: "+ex.getMessage());
				FeatureId failedFeatureId = new FeatureId(item.getTitle(),item.getClient());
				response.getFailedToActionFeatures().add(failedFeatureId);
			}
		}
		
		return response;
	}
	
	
	
}
