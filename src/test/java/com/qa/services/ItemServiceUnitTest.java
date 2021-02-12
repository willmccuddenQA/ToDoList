package com.qa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.persistence.domain.ItemDomain;
import com.qa.persistence.dtos.ItemDTO;
import com.qa.persistence.repos.ItemRepo;

@SpringBootTest
public class ItemServiceUnitTest {
	@MockBean 
	private ModelMapper mockMapper;
	
	@MockBean
	private ItemRepo mockRepo;
	
	@Autowired
	private ItemService service;
	
	@Test
	public void create() {
		//RESOURCES
		ItemDomain entry = new ItemDomain(1L,"Go to shop","Tomorrow",false,null);
		ItemDTO expectedResult = new ItemDTO(1L,"Go to shop","Tomorrow",false,null);
		
		//RULES
		Mockito.when(this.mockRepo.save(entry)).thenReturn(entry); 
		Mockito.when(this.mockMapper.map(entry,ItemDTO.class)).thenReturn(expectedResult);
		
		//ACTIONS
		ItemDTO result = this.service.addItem(entry);
		
		//ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockRepo, Mockito.times(1)).save(Mockito.any(ItemDomain.class));
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(entry, ItemDTO.class);
	}
	
	@Test
	public void readAll() {
		// RESOURCES
		ItemDomain entry1 = new ItemDomain(1L,"Go to shop","Tomorrow",false,null);
		ItemDomain entry2 = new ItemDomain(2L,"Go to shop","Next Week",false,null);
		ItemDTO DTO1 = new ItemDTO(1L,"Go to shop","Tomorrow",false,null);
		ItemDTO DTO2 = new ItemDTO(2L,"Go to shop","Next Week",false,null);

		List<ItemDomain> entries = new ArrayList<>();
		entries.add(entry1);
		entries.add(entry2);

		List<ItemDTO> DTOS = new ArrayList<>();
		DTOS.add(DTO1);
		DTOS.add(DTO2);

		// RULES
		Mockito.when(this.mockRepo.findAll()).thenReturn(entries);
		Mockito.when(this.mockMapper.map(entry1, ItemDTO.class)).thenReturn(DTO1);
		Mockito.when(this.mockMapper.map(entry2, ItemDTO.class)).thenReturn(DTO2);

		// ACTIONS
		List<ItemDTO> result = this.service.getAllItems();

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(DTOS);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(DTOS);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockRepo, Mockito.times(1)).findAll();
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(entry1, ItemDTO.class);
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(entry2, ItemDTO.class);
	}
	
	@Test
	public void readOne() {
		// RESOURCES
		ItemDomain entry = new ItemDomain(1L,"Go to shop","Tomorrow",false,null);
		ItemDTO expectedResult = new ItemDTO(1L,"Go to shop","Tomorrow",false,null);

		// RULES
		Mockito.when(this.mockRepo.findById(entry.getId())).thenReturn(Optional.of(entry));
		Mockito.when(this.mockMapper.map(entry, ItemDTO.class)).thenReturn(expectedResult);

		// ACTIONS
		ItemDTO result = this.service.getOneItem(entry.getId());

		// ASSERTIONS
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Mockito.verify(this.mockRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(entry, ItemDTO.class);
	}
	
	@Test
	public void delete() {

		// RULES
		Mockito.when(this.mockRepo.existsById(1L)).thenReturn(false);

		// ACTIONS
		boolean result = this.service.removeItem(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(true);
		Mockito.verify(this.mockRepo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.mockRepo, Mockito.times(1)).existsById(1L);
	}
	
	@Test
	public void update() {
		// RESOURCES
		ItemDomain entry = new ItemDomain(1L,"Go to shop","Tomorrow",false,null);
		ItemDomain updated = new ItemDomain(1L,"Go to shop","Next Week",false,null);
		ItemDTO expectedResult = new ItemDTO(1L,"Go to shop","Next Week",false,null);

		// RULES
		Mockito.when(this.mockRepo.findById(1L)).thenReturn(Optional.of(entry)); 
		Mockito.when(this.mockRepo.save(Mockito.any(ItemDomain.class))).thenReturn(updated);
		Mockito.when(this.mockMapper.map(updated, ItemDTO.class)).thenReturn(expectedResult);

		// ACTIONS
		ItemDTO result = this.service.updateItem(1L,updated); 

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockRepo, Mockito.times(1)).save(Mockito.any(ItemDomain.class));
		Mockito.verify(this.mockMapper, Mockito.times(1)).map(updated, ItemDTO.class);
	}
}
