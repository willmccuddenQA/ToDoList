package com.qa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.modelmapper.ModelMapper;

import com.qa.persistence.domain.ListDomain;
import com.qa.persistence.dtos.ListDTO;
import com.qa.persistence.repos.ListRepo;

@SpringBootTest
public class ListServiceUnitTest {
	
	@MockBean 
	private ModelMapper mockMapper;
	
	@MockBean
	private ListRepo mockRepo;
	
	@Autowired
	private ListService service;
	
	@Test
	public void create() {
		//RESOURCES
		ListDomain entry = new ListDomain(1L,"Important List",null);
		ListDTO expectedResult = new ListDTO(1L,"Important List");
		
		//RULES
		Mockito.when(this.mockRepo.save(entry)).thenReturn(entry); 
		Mockito.when(this.mockMapper.map(entry,ListDTO.class)).thenReturn(expectedResult);
		
		//ACTIONS
		ListDTO result = this.service.addList(entry);
		
		//ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockRepo, Mockito.times(1)).save(Mockito.any(ListDomain.class));
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(entry, ListDTO.class);
	}
	
	@Test
	public void readAll() {
		// RESOURCES
		ListDomain entry1 = new ListDomain(1L,"Important List",null);
		ListDomain entry2 = new ListDomain(2L,"Less Important List",null);
		ListDTO DTO1 = new ListDTO(1L,"Important List");
		ListDTO DTO2 = new ListDTO(2L,"Less Important List");

		List<ListDomain> entries = new ArrayList<>();
		entries.add(entry1);
		entries.add(entry2);

		List<ListDTO> DTOS = new ArrayList<>();
		DTOS.add(DTO1);
		DTOS.add(DTO2);

		// RULES
		Mockito.when(this.mockRepo.findAll()).thenReturn(entries);
		Mockito.when(this.mockMapper.map(entry1, ListDTO.class)).thenReturn(DTO1);
		Mockito.when(this.mockMapper.map(entry2, ListDTO.class)).thenReturn(DTO2);

		// ACTIONS
		List<ListDTO> result = this.service.getAllLists();

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(DTOS);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(DTOS);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockRepo, Mockito.times(1)).findAll();
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(entry1, ListDTO.class);
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(entry2, ListDTO.class);
	}
	
	@Test
	public void readOne() {
		// RESOURCES
		ListDomain entry = new ListDomain(1L,"Important List",null);
		ListDTO expectedResult = new ListDTO(1L,"Important List");

		// RULES
		Mockito.when(this.mockRepo.findById(entry.getId())).thenReturn(Optional.of(entry));
		Mockito.when(this.mockMapper.map(entry, ListDTO.class)).thenReturn(expectedResult);

		// ACTIONS
		ListDTO result = this.service.getOneList(entry.getId());

		// ASSERTIONS
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Mockito.verify(this.mockRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(entry, ListDTO.class);
	}
	
	@Test
	public void delete() {

		// RULES
		Mockito.when(this.mockRepo.existsById(1L)).thenReturn(false);

		// ACTIONS
		boolean result = this.service.removeList(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(true);
		Mockito.verify(this.mockRepo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.mockRepo, Mockito.times(1)).existsById(1L);
	}
	
	@Test
	public void update() {
		// RESOURCES
		ListDomain entry = new ListDomain(1L,"Important List",null);
		ListDomain updated = new ListDomain(1L,"Important List Updated",null);
		ListDTO expectedResult = new ListDTO(1L,"Important List Updated");

		// RULES
		Mockito.when(this.mockRepo.findById(1L)).thenReturn(Optional.of(entry)); 
		Mockito.when(this.mockRepo.save(Mockito.any(ListDomain.class))).thenReturn(updated);
		Mockito.when(this.mockMapper.map(updated, ListDTO.class)).thenReturn(expectedResult);

		// ACTIONS
		ListDTO result = this.service.updateList(1L,updated); 

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockRepo, Mockito.times(1)).save(Mockito.any(ListDomain.class));
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(updated, ListDTO.class);
	}
}
