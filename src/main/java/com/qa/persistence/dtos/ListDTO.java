package com.qa.persistence.dtos;


public class ListDTO {

	private String title;
	private Long id;

	public ListDTO(Long id, String title) {
		super();
		this.title = title;
		this.id = id;
	}

	public ListDTO() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
