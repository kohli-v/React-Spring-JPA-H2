package com.wcf.client.Backend.Resources;

import java.util.ArrayList;
import java.util.List;
import com.wcf.client.Backend.Entities.FeatureId;

public class EnableFeatureResponseResource {

	private String result;
	private List<FeatureId> failedToActionFeatures;
	
	
	public EnableFeatureResponseResource() {
		this.failedToActionFeatures = new ArrayList<FeatureId>();
	}


	public List<FeatureId> getFailedToActionFeatures() {
		return failedToActionFeatures;
	}


	public void setFailedToActionFeatures(List<FeatureId> failedToActionFeatures) {
		this.failedToActionFeatures = failedToActionFeatures;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
}
