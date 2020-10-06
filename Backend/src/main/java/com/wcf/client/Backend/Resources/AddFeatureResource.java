package com.wcf.client.Backend.Resources;


public class AddFeatureResource {

	
	
	private String title,description, productArea, targetDate;
	private char client;
	private int clientPriority;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductArea() {
		return productArea;
	}
	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}
	public char getClient() {
		return client;
	}
	public void setClient(char client) {
		this.client = client;
	}
	public int getClientPriority() {
		return clientPriority;
	}
	public void setClientPriority(int clientPriority) {
		this.clientPriority = clientPriority;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	
}
