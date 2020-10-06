package com.wcf.client.Backend.Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FeatureId implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	char client;
	
	FeatureId(){};
	public FeatureId(String title, char client) {
		this.title = title;
		this.client = client;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public char getClient() {
		return client;
	}

	public void setClient(char client) {
		this.client = client;
	}

	
}
