package com.revature.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.models.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Autowired
	public MockMvc mvc;
	
	@Test
	public void register() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/customer/register");
		
		Customer anObject = new Customer();
	    anObject.setUsername("testyboy");
	    anObject.setPassword("4268321");
	    anObject.setFirstName("Test");
	    anObject.setLastName("Test");
	    anObject.setAddress("Test");
	    anObject.setAddress2("Test");
	    anObject.setCity("Test");
	    anObject.setState("TT");
	    anObject.setZip(111111);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(anObject);
	    
	    
	    MvcResult result = mvc.perform(post("/customer/register").contentType(APPLICATION_JSON_UTF8)
	    		.content(requestJson)
	    		).andReturn();
	    
	    assertEquals("Username already in use!", result.getResponse().getContentAsString());
	}

}
