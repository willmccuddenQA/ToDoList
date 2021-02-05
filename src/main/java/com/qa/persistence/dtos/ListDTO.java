package com.qa.persistence.dtos;


public class ListDTO {

	private String title;

	public ListDTO(String title) {
		super();
		this.title = title;
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
}
