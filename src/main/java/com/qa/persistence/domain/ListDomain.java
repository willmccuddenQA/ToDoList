package com.qa.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ListDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	@OneToMany(mappedBy = "list", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ItemDomain> itemList;

	public ListDomain(Long id, String title, List<ItemDomain> itemList) {
		super();
		this.id = id;
		this.title = title;
		this.itemList = itemList;
	}

	public ListDomain() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ItemDomain> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemDomain> itemList) {
		this.itemList = itemList;
	}
	
	
}
