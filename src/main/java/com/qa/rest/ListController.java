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

import com.qa.persistence.domain.ListDomain;
import com.qa.services.ListService;

@RestController
@RequestMapping("list")
public class ListController {
	
	private ListService service;
	
	@Autowired
	public ListController(ListService service) {
		this.service=service;
	}
	

	@PostMapping("/create")
	public ResponseEntity<ListDomain> createlist(@RequestBody ListDomain list) {
		return new ResponseEntity<ListDomain>( this.service.addList(list),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ListDomain>> readAll() {
		return new ResponseEntity<List<ListDomain>>(this.service.getAllLists(),HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ListDomain> update(@PathVariable("id") Long id, @RequestBody ListDomain list) {
	    return new ResponseEntity<ListDomain>(service.updateList(id, list),HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		return this.service.removeList(id) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT):
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<ListDomain> readOne(@PathVariable("id") Long id) {
		return new ResponseEntity<ListDomain>(service.getOneList(id),HttpStatus.OK);
	}
}
