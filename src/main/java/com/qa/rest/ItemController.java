package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.ItemDomain;
import com.qa.persistence.dtos.ItemDTO;
import com.qa.services.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	private ItemService service;
	
	@Autowired
	public ItemController(ItemService service) {
		super();
		this.service=service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ItemDomain> createItem(@RequestBody ItemDomain item) {
		return new ResponseEntity<ItemDomain>( this.service.addItem(item),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ItemDomain>> readAll() {
		return new ResponseEntity<List<ItemDomain>>(this.service.getAllItems(),HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ItemDomain> update(@PathVariable("id") Long id, @RequestBody ItemDomain item) {
	    return new ResponseEntity<ItemDomain>(service.updateItem(id, item),HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		return this.service.removeItem(id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT):
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<ItemDomain> readOne(@PathVariable("id") Long id) {
		return new ResponseEntity<ItemDomain>(service.getOneItem(id),HttpStatus.OK);
	}
	
	

}