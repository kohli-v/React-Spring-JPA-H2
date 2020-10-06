package com.wcf.client.Backend.Services;

import java.util.List;

import com.wcf.client.Backend.Entities.Feature;
import com.wcf.client.Backend.Entities.FeatureId;
import com.wcf.client.Backend.Resources.AddFeatureResource;
import com.wcf.client.Backend.Resources.DeleteFeatureResponseResource;
import com.wcf.client.Backend.Resources.EnableFeatureResponseResource;

public interface IManageFeatureService {

	List<Feature> retrieveFeatureList();

	Feature addFeature(AddFeatureResource addFeatureResource);

	DeleteFeatureResponseResource deleteFeature(List<FeatureId> featureIdList);

	EnableFeatureResponseResource enableFeature(List<FeatureId> featureIdList);

}
