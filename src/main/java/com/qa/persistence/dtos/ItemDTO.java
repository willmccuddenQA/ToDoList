package com.qa.persistence.dtos;

public class ItemDTO {
	
	private String description;
	private String completeBy;
	private Boolean completeStatus;

	public ItemDTO(String description, String completeBy, Boolean completeStatus) {
		super();
		this.description = description;
		this.completeBy = completeBy;
		this.completeStatus = completeStatus;
	}

	public ItemDTO() {
		super();
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompleteBy() {
		return completeBy;
	}

	public void setCompleteBy(String completeBy) {
		this.completeBy = completeBy;
	}

	public Boolean getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(Boolean completeStatus) {
		this.completeStatus = completeStatus;
	}
}
