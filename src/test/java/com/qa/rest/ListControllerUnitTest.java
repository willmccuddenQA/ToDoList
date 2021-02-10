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

import com.qa.persistence.domain.ListDomain;
import com.qa.persistence.dtos.ListDTO;
import com.qa.services.ListService;

@SpringBootTest
public class ListControllerUnitTest {

	@MockBean
	private ModelMapper mockMapper;

	@MockBean
	private ListService mockService;

	@Autowired
	private ListController mockController;

	@Test
	public void create() {
		// RESOURCES
		ListDomain entry = new ListDomain(1L, "Tomorrow's list", null);
		ListDTO storedDTO = new ListDTO("Tomorrow's list");
		ResponseEntity<ListDTO> expectedResult = new ResponseEntity<ListDTO>(storedDTO, HttpStatus.CREATED);

		// RULES
		Mockito.when(this.mockService.addList(entry)).thenReturn(storedDTO);

		// ACTIONS
		ResponseEntity<ListDTO> result = this.mockController.create(entry);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).addList(entry);
	}

	@Test
	public void readAll() {

		// RESOURCES
		ListDTO expectedResult1 = new ListDTO("Tomorrow's list");
		ListDTO expectedResult2 = new ListDTO("Today's list");
		List<ListDTO> storedDTOs = new ArrayList<>();
		storedDTOs.add(expectedResult1);
		storedDTOs.add(expectedResult2);
		ResponseEntity<List<ListDTO>> expectedResult = new ResponseEntity<List<ListDTO>>(storedDTOs, HttpStatus.OK);

		// RULES
		Mockito.when(this.mockService.getAllLists()).thenReturn(storedDTOs);

		// ACTIONS
		ResponseEntity<List<ListDTO>> result = this.mockController.readAll();

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).getAllLists();
	}

	@Test
	public void readOne() {
		// RESOURCES
		ListDTO storedDTO = new ListDTO("Tomorrow's list");
		ResponseEntity<ListDTO> expectedResult = new ResponseEntity<ListDTO>(storedDTO, HttpStatus.OK);

		// RULES
		Mockito.when(this.mockService.getOneList(1L)).thenReturn(storedDTO);

		// ACTIONS
		ResponseEntity<ListDTO> result = this.mockController.readOne(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).getOneList(1L);
	}

	@Test
	public void delete() {

		// RESOURCES
		ResponseEntity<ListDTO> expectedResult = new ResponseEntity<ListDTO>(HttpStatus.NO_CONTENT);

		// RULES
		Mockito.when(this.mockService.removeList(1L)).thenReturn(true);

		// ACTIONS
		ResponseEntity<Boolean> result = this.mockController.delete(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).removeList(1L);
	}

	@Test
	public void update() {
		// RESOURCES
		ListDomain entry = new ListDomain(1L, "Tomorrow's list", null);
		ListDTO storedDTO = new ListDTO("Tomorrow's list");
		ResponseEntity<ListDTO> expectedResult = new ResponseEntity<ListDTO>(storedDTO,HttpStatus.ACCEPTED);

		// RULES
		Mockito.when(this.mockService.updateList(1L, entry)).thenReturn(storedDTO);

		// ACTIONS
		ResponseEntity<ListDTO> result = this.mockController.update(1L, entry);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(expectedResult);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
		Assertions.assertThat(result).isNotNull();
		Mockito.verify(this.mockService, Mockito.times(1)).updateList(1L, entry);
	}
}
