package com.wcf.client.Backend.Resources;

public enum FeatureStatusList {

	DISABLED("Disabled"),
	ENABLED("Enabled"),
	IN_DEVELOPMENT("In Development");
	
	private String status;
	
	FeatureStatusList(String status) {
		this.status = status;
	}
	@Override
    public String toString() {
        return status;
    }
}
