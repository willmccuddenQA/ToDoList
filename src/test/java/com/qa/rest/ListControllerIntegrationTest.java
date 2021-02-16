//package com.qa.rest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.qa.persistence.domain.ListDomain;
//import com.qa.persistence.dtos.ListDTO;
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@Sql(scripts = { "classpath:schema-test.sql",
//		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//@ActiveProfiles(profiles = "test")
//public class ListControllerIntegrationTest {
//
//	@Autowired
//	private MockMvc mock;
//
//	@Autowired
//	private ObjectMapper jsonifier;
//	
//	@Test
//	public void create() throws Exception {
//
//		// resources
//		ListDomain entry = new ListDomain(4L,"Next Week",null);
//		ListDTO expectedResult = new ListDTO(4L,"Next Week");
//
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//				.request(HttpMethod.POST, "http://localhost:8080/list/create").contentType(MediaType.APPLICATION_JSON)
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
//		List<ListDTO> expectedResult = new ArrayList<>();
//		expectedResult.add(new ListDTO(1L,"Todays jobs"));
//		expectedResult.add(new ListDTO(2L,"Targets for the year"));
//		expectedResult.add(new ListDTO(3L,"Shopping List"));
//
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
//				"http://localhost:8080/list/getAll");
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
//		ListDomain entry = new ListDomain(3L,"Next Week",null);
//		ListDTO expectedResult = new ListDTO(3L,"Next Week");
//
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//				.request(HttpMethod.PUT, "http://localhost:8080/list/update/3").contentType(MediaType.APPLICATION_JSON)
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
//				.request(HttpMethod.DELETE, "http://localhost:8080/list/delete/1");
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
//		ListDTO expectedResult = new ListDTO(1L,"Todays jobs");
//
//		// set up request
//		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
//				"http://localhost:8080/list/get/" + 1);
//
//		// set up action
//		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
//		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
//
//		// Perform
//		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
//	}
//}
