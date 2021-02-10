package com.qa.rest;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.persistence.domain.ItemDomain;
import com.qa.persistence.dtos.ItemDTO;
import com.qa.services.ItemService;

@SpringBootTest
public class ItemControllerUnitTest {

	@MockBean
	private ModelMapper mockMapper;

	@MockBean
	private ItemService mockService;

	@Autowired
	private ItemController mockController;

	@Test
	public void create() {
		// RESOURCES
		ItemDomain entry = new ItemDomain(1L, "Go to shop", "Tomorrow", false, null);
		ItemDTO storedDTO = new ItemDTO("Go to shop", "Tomorrow", false);
		ResponseEntity<ItemDTO> expectedResult = new ResponseEntity<ItemDTO>(storedDTO,HttpStatus.CREATED);

		// RULES
		Mockito.when(this.mockService.addItem(entry)).thenReturn(storedDTO);

		// ACTIONS
		ResponseEntity<ItemDTO> result = this.mockController.createItem(entry);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).addItem(entry);
	}

	@Test
	public void readAll() {

		// RESOURCES
		ItemDTO DTO1 = new ItemDTO("Go to shop", "Tomorrow", false);
		ItemDTO DTO2 = new ItemDTO("Go to shop", "Next Week", false);
		List<ItemDTO> storedDTOs = new ArrayList<>();
		storedDTOs.add(DTO1);
		storedDTOs.add(DTO2);
		ResponseEntity <List<ItemDTO>> expectedResult = new ResponseEntity<List<ItemDTO>>(storedDTOs,HttpStatus.OK);

		// RULES
		Mockito.when(this.mockService.getAllItems()).thenReturn(storedDTOs);

		// ACTIONS
		ResponseEntity<List<ItemDTO>> result = this.mockController.readAll();

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).getAllItems();
	}

	@Test
	public void readOne() {
		// RESOURCES
		ItemDTO storedDTO = new ItemDTO("Go to shop", "Tomorrow", false);
		ResponseEntity<ItemDTO> expectedResult = new ResponseEntity<ItemDTO>(storedDTO,HttpStatus.OK);

		// RULES
		Mockito.when(this.mockService.getOneItem(1L)).thenReturn(storedDTO);

		// ACTIONS
		ResponseEntity<ItemDTO> result = this.mockController.readOne(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).getOneItem(1L);
	}

	@Test
	public void delete() {

		//RESOURCES
		ResponseEntity<ItemDTO> expectedResult = new ResponseEntity<ItemDTO>(HttpStatus.NO_CONTENT);
		
		// RULES
		Mockito.when(this.mockService.removeItem(1L)).thenReturn(true);

		// ACTIONS
		ResponseEntity<Boolean> result = this.mockController.delete(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).removeItem(1L);
	}

	@Test
	public void update() {
		// RESOURCES
		ItemDomain entry = new ItemDomain(1L, "Go to shop", "Tomorrow", false, null);
		ItemDTO storedDTO = new ItemDTO("Go to shop", "Tomorrow", false);
		ResponseEntity<ItemDTO> expectedResult = new ResponseEntity<ItemDTO>(storedDTO,HttpStatus.ACCEPTED);

		// RULES
		Mockito.when(this.mockService.updateItem(1L, entry)).thenReturn(storedDTO);

		// ACTIONS
		ResponseEntity<ItemDTO> result = this.mockController.update(1L, entry);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).updateItem(1L, entry);
	}
}
