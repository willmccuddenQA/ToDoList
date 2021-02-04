package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ItemDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	private String description;
	private String completeBy;
	private Boolean completeStatus;
	private Long list_id;
	
	public ItemDomain(Long id, String description, String completeBy, Boolean completeStatus, Long list_id) {
		super();
		this.id = id;
		this.description = description;
		this.completeBy = completeBy;
		this.completeStatus = completeStatus;
		this.list_id = list_id;
	}

	public ItemDomain() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getList_id() {
		return list_id;
	}

	public void setList_id(Long list_id) {
		this.list_id = list_id;
	}
	
}
