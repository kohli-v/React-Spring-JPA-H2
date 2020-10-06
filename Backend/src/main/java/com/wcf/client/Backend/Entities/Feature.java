package com.wcf.client.Backend.Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(FeatureId.class)
@Table(name="feature_list_table")  
public class Feature implements Serializable{
	
	public void setClientPriority(int clientPriority) {
		this.clientPriority = clientPriority;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String title;

	@Id
	private char client;

	private String description;
	
	@Column(name="product_area")
	private String productArea;
	
	@Column(name="feature_status")
	private String featureStatus;
	
	@Column(name="client_priority")
	private int clientPriority;
	
	@Column(name="target_date")
	private String targetDate;
	
	Feature(){};
	
	public Feature (String title, char client, String description, 
			String productArea, String featureStatus, 
			int clientPriority, String targetDate){
		
		this.title = title;
		this.client = client;
		this.description = description;
		this.productArea = productArea;
		this.featureStatus = featureStatus;
		this.clientPriority = clientPriority;
		this.targetDate = targetDate;
		
	}
	
	public String getDescription() {
		return description;
	}

	public String getProductArea() {
		return productArea;
	}

	public String getFeatureStatus() {
		return featureStatus;
	}

	public int getClientPriority() {
		return clientPriority;
	}

	public String getTargetDate() {
		return targetDate;
	}
	public String getTitle() {
		return title;
	}

	public void setFeatureStatus(String featureStatus) {
		this.featureStatus = featureStatus;
	}

	public char getClient() {
		return client;
	}
}
