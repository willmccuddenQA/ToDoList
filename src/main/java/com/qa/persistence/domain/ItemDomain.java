package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ItemDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String completeBy;
	private Boolean completeStatus;
	
	@ManyToOne
	@JoinColumn(name = "list_id")
	private ListDomain list;

	public ItemDomain(Long id, String description, String completeBy, Boolean completeStatus, ListDomain list) {
		super();
		this.id = id;
		this.description = description;
		this.completeBy = completeBy;
		this.completeStatus = completeStatus;
		this.list = list;
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

	public ListDomain getList() {
		return list;
	}

	public void setList(ListDomain list) {
		this.list = list;
	}
	
	
	
}
