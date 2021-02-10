package com.qa.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.ItemDomain;
import com.qa.persistence.dtos.ItemDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ItemControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper jsonifier;

//	@Test
//	public void create() throws Exception {
//
//		// resources
//		ItemDomain entry = new ItemDomain(8L, "Thing", "Now", false, null);
//		ItemDTO expectedResult = new ItemDTO("Thing", "Now", false);
//
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//				.request(HttpMethod.POST, "http://localhost:8080/item/create").contentType(MediaType.APPLICATION_JSON)
//				.content(jsonifier.writeValueAsString(entry)).accept(MediaType.APPLICATION_JSON);
//
//		// set up action
//		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
//		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
//
//		// Perform
//		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
//	}
//
//	@Test
//	public void readAll() throws Exception {
//		// resources
//		List<ItemDTO> expectedResult = new ArrayList<>();
//		expectedResult.add(new ItemDTO("Milk", "Midday", false));
//		expectedResult.add(new ItemDTO("Bread", "Midday", false));
//		expectedResult.add(new ItemDTO("Butter", "Midday", false));
//		expectedResult.add(new ItemDTO("Tidy room", "Today", true));
//		expectedResult.add(new ItemDTO("Take bins out", "Today", true));
//		expectedResult.add(new ItemDTO("Read 12 books", "31st December", false));
//		expectedResult.add(new ItemDTO("Learn to do a rubiks cube", "31st December", false));
//
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
//				"http://localhost:8080/item/getAll");
//
//		// set up action
//		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
//		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
//
//		// Perform
//		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
//	}
//
//	@Test
//	public void update() throws Exception {
//		// resources
//		ItemDomain entry = new ItemDomain(5L, "Thing", "Now", false, null);
//		ItemDTO expectedResult = new ItemDTO("Thing", "Now", false);
//
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//				.request(HttpMethod.PUT, "http://localhost:8080/item/update/5").contentType(MediaType.APPLICATION_JSON)
//				.content(jsonifier.writeValueAsString(entry)).accept(MediaType.APPLICATION_JSON);
//
//		// set up action
//		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
//		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
//
//		// Perform
//		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
//	}
//
//	@Test
//	public void delete() throws Exception {
//		
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//				.request(HttpMethod.DELETE, "http://localhost:8080/item/delete/7");
//
//		// set up action
//		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();;
//
//		// Perform
//		this.mock.perform(mockRequest).andExpect(matchStatus);
//	}
//
//	@Test
//	public void readOne() throws Exception {
//
//		// resources
//		ItemDTO expectedResult = new ItemDTO("Milk", "Midday", false);
//
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
//				"http://localhost:8080/item/get/" + 1);
//
//		// set up action
//		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
//		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
//
//		// Perform
//		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
//	}

}