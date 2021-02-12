package com.qa.persistence.dtos;

public class ItemDTO {
	
	private String description;
	private String completeBy;
	private Boolean completeStatus;
	private Long id;
	private ListDTO list;
	
	public ItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemDTO(String description, String completeBy, Boolean completeStatus, Long id, ListDTO list) {
		super();
		this.description = description;
		this.completeBy = completeBy;
		this.completeStatus = completeStatus;
		this.id = id;
		this.list = list;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ListDTO getList() {
		return list;
	}

	public void setList(ListDTO list) {
		this.list = list;
	}

	
}
